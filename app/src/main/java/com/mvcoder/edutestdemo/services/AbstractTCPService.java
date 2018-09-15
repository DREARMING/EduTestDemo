package com.mvcoder.edutestdemo.services;

import android.app.Service;
import android.content.Intent;

import com.mvcoder.edutestdemo.bean.IOBuffer;
import com.mvcoder.edutestdemo.utils.LogUtil;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public abstract class AbstractTCPService extends Service {

    protected byte[] heartData;
    protected int port;
    protected String IP;
    private final int HEAR_BEAT_PERIOD = 5;
    private static final int SOCKET_CONNECT_TIMEOUT = 5;
    private volatile boolean done = false;
    private volatile boolean canRW = true;
    Socket clientSocket = null;
    SocketAddress socketAddress = null;

    private BufferedInputStream reader;
    private int bufferSize=8192;

    private boolean isFirstCononect = false;

    private PrintWriter writer;
    private HeartBeatThread heartBeatThread;
    private ReconnectionThread reconnectionThread;
    private OutputStream os;
    private ITCPHandler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        IP = getIP();
        port = getPort();
        heartData = getHeartData();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isFirstCononect = true;
        if (clientSocket == null || (clientSocket != null && clientSocket.isClosed())) {
            //网络连接用子线程开启，失败自动重连
            startReconnect();
        }
        return START_REDELIVER_INTENT;
    }

    private void startReconnect() {
        if (reconnectionThread == null || !reconnectionThread.isAlive()) {
            //为了防止reconnectionThread在多个方法被调用时，重复调用start方法，所以重新new一个出来
            reconnectionThread = new ReconnectionThread();
            reconnectionThread.start();
        }
    }

    private void connect() {
        if(done) return;
        LogUtil.d("connect method be called");
        try {
            socketAddress = new InetSocketAddress(IP, port);
            clientSocket = new Socket();
            clientSocket.connect(socketAddress, SOCKET_CONNECT_TIMEOUT * 1000);
            LogUtil.d("connected");
            if(!isFirstCononect){
                serviceReconnect();
            }
            isFirstCononect = false;
            canRW = true;
            if(os != null){
                os.close();
            }
            if (writer != null) {
                writer.close();
            }
            os = clientSocket.getOutputStream();
            writer = new PrintWriter(os,true);
            new Thread(new WriteRunnable()).start();

            if (reader != null) {
                reader.close();
            }
            //更改reader
            //reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "utf-8"));
            reader = new BufferedInputStream(clientSocket.getInputStream());

            new Thread(new ReadRunnable()).start();

            //成功连接上服务器后，开启心跳进程
            startHeartBearThread();
        } catch (IOException e) {
            LogUtil.d(e.toString());
            LogUtil.d("client socket connected : " + clientSocket.isConnected());
            if(clientSocket.isConnected()){
                try {
                    clientSocket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
            if (reconnectionThread == null || !clientSocket.isConnected()) {
                startReconnect();
            }
        }
    }

    class WriteRunnable implements Runnable {

        @Override
        public void run() {
            while (!done && canRW) {
                try {
                    synchronized (writer) {
                        // writer.println("hello server:" + System.currentTimeMillis());
                        // LogUtil.d("client write one msg");
                        //os.write(command,0,command.length);
                    }
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                    if (!done) {
                        if(clientSocket.isConnected() && !clientSocket.isClosed()){
                            try {
                                writer.close();
                                clientSocket.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        startReconnect();
                    }
                }
            }
        }
    }

    class ReadRunnable implements Runnable {

        @Override
        public void run() {
            while (!done && canRW) {
                try {
                    byte[] buffer = new byte[1024];
                    IOBuffer ioBuffer = new IOBuffer();
                    int num = -1;
                    boolean hasRemainning = false;
                    //hasRemaining起到断电的作用
                    while(hasRemainning || (num = reader.read(buffer)) != -1){
                        if(!hasRemainning)
                            ioBuffer.write(buffer,0,num);
                        if(ioBuffer.size() < 4){
                            hasRemainning = false;
                            continue;
                        }
                        int len = ioBuffer.readInt();
                        if(ioBuffer.size() - 4 < len) {
                            hasRemainning = false;
                            continue;
                        }
                        byte[] content = new byte[len];
                        ioBuffer.readBytes(content,4);
                        analysisCommand(content);
                        //有可能后面还有数据，应该继续读完，而不是马上从流里面读
                        hasRemainning = true;
                        //LogUtil.d("数据内容：" + new String(content));
                    }
                    ioBuffer.reset();
                    ioBuffer.close();
                    canRW = false;
                    reader.close();
                    clientSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    if(!done){
                        if(clientSocket.isConnected() && !clientSocket.isClosed()){
                            try {
                                canRW = false;
                                reader.close();
                                clientSocket.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                    LogUtil.d("read error" + e.getStackTrace().toString() + " \n " + e.getMessage());
                    if (!done)
                        startReconnect();
                }

            }
        }
    }

    private void analysisCommand(byte[] command) {
        if(command == null || command.length < 8) return;
        handler.handle(command);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d("ondestroy");
        // Network.getInstance().cancel(TAG);
        isFirstCononect = false;
        done = true;
        //当服务被销毁时，意味着要主动断开连接了
        if (heartBeatThread != null && heartBeatThread.isAlive()) {
            heartBeatThread.interrupt();
        }
        if (reconnectionThread != null && reconnectionThread.isAlive()) {
            reconnectionThread.interrupt();
        }
        if (clientSocket != null && (clientSocket.isConnected() && !clientSocket.isClosed())) {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(os != null)
                        os.close();
                    if(writer != null)
                        writer.close();
                    if(reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void startHeartBearThread() {
        heartBeatThread = new HeartBeatThread();
        heartBeatThread.start();
    }

    class HeartBeatThread extends Thread {

        @Override
        public void run() {
            boolean endFlag = false;
            while (!done && !isInterrupted() && !endFlag) {
                try {
                    // 5秒发一次心跳包，对客户端进行保活
                    Thread.sleep(HEAR_BEAT_PERIOD * 1000);
                    synchronized (os) {
                        //当服务端将socket连接断开时，发送UrgentData会报异常，这是捕捉服务器断开连接的标识
                        // clientSocket.sendUrgentData(0xff);
                        //发送心跳数据
                        os.write(heartData,0,heartData.length);
                        os.flush();
                        //    LogUtil.d(this.getName() +  "sendTime after: " + System.currentTimeMillis() / 1000);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    // LogUtil.d("client socket disconnected");
                    //  LogUtil.d("close ? " + clientSocket.isConnected() + " , connected?" + clientSocket.isClosed());
                    //可能网络发生改变，IP地址变了，或者网络状况很差，或者服务器出现问题，导致发送数据异常
                    //这个时候需要有重连机制
                    if (!done)
                        try {
                            //终止读写两条线程
                            canRW = false;
                            reader.close();
                            os.close();
                            writer.close();
                            clientSocket.close();
                            endFlag = true;
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    startReconnect();
                }
            }
        }
    }

    class ReconnectionThread extends Thread {

        private int wait = 0;

        @Override
        public void run() {
            super.run();
            if (clientSocket == null) {
                clientSocket = new Socket();
            }
            //线程被打断，或者当前socket连接上Server后，就不应该重连了
            //注意：socket在初始化后， isConnect = false; isClosed = false;  socket非初始化后，并且连接上一次服务器，
            // 那么socket的isConnected永远返回true，需要根据isClose来判断是否连接上
            while (!done && !isInterrupted() && !(clientSocket.isConnected() && !clientSocket.isClosed())) {
                try {
                    wait++;
                    LogUtil.d("reconnect...");
                    connect();
                    Thread.sleep(getWait() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            wait = 0;
        }

        private int getWait() {
            //超过20次失败，证明网络真的很差，10分钟重连一次
            if (wait > 20) return 600;
            //超过13次，5分钟重连一次
            if (wait > 13) return 300;
            //少于7次，10秒钟重连一次，大于7而小于13，则1分钟重连一次
            return wait < 7 ? 10 : 60;
        }
    }

    abstract String getIP();

    abstract int getPort();

    abstract byte[] getHeartData();

    abstract void serviceReconnect();

}
