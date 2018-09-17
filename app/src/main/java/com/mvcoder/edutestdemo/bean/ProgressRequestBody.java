package com.mvcoder.edutestdemo.bean;

import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public class ProgressRequestBody extends RequestBody {

    private RequestBody mDelegateBody;

    private BufferedSink mBufferedSink;

    public ProgressRequestBody(RequestBody originalBody){
        this.mDelegateBody = originalBody;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return mDelegateBody.contentType();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if (mBufferedSink == null) {
            mBufferedSink = Okio.buffer(wrapSink(sink));
        }
        mDelegateBody.writeTo(mBufferedSink);
        mBufferedSink.flush();
    }

    private Sink wrapSink(Sink sink) {

        return new ForwardingSink(sink) {

            long writeBytes = 0;
            long contentLength = 0;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if(contentLength == 0){
                    contentLength();
                }
                writeBytes += byteCount;
            }
        };
    }
}
