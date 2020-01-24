package edu.apaczai.service.executor;

import android.util.Log;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;

public final class CallExecutor {
    private static final String NAME = CallExecutor.class.getName();
    private static CallExecutor INSTANCE;

    private CallExecutor() {
    }

    final class CallExecutorException extends Exception {
        public CallExecutorException() {
            super();
        }

        public CallExecutorException(final String message) {
            super(message);
        }
    }

    public static CallExecutor getInstance() {
        synchronized (CallExecutor.class) {
            if (Objects.isNull(INSTANCE)) {
                INSTANCE = new CallExecutor();
            }
        }

        return INSTANCE;
    }

    public <T> void execute(
            final Call<T> call,
            CallResponseCallback<T> responseCallback,
            CallFailedCallback failedCallback
    ) {
        Thread thread = new Thread(() -> {
            try {
                Response<T> response = call.execute();
                if (response.isSuccessful()) {
                    responseCallback.callback(response.body());
                } else {
                    if (response.code() == 404) {
                        failedCallback.handle(new CallExecutorException("RESOURCE NOT FOUND"));
                    } else {
                        failedCallback.handle(new CallExecutorException(response.message()));
                    }
                }
            } catch (IOException e) {
                Log.e(NAME, "Execute error", e);
                failedCallback.handle(e);
            }
        });

        thread.start();
    }

    public <T> void execute(
            final Call<T> call
    ) {
        Thread thread = new Thread(() -> {
            try {
                Response<T> response = call.execute();
                if (!response.isSuccessful()) {
                    throw new CallExecutorException(response.code() + " " + response.message());
                }
            } catch (CallExecutorException | IOException e) {
                Log.e(NAME, "Execute error", e);
            }
        });

        thread.start();
    }

    public <T> void execute(
            final Call<T> call,
            CallResponseCallback<T> responseCallback
    ) {
        Thread thread = new Thread(() -> {
            try {
                Response<T> response = call.execute();
                if (response.isSuccessful()) {
                    responseCallback.callback(response.body());
                } else {
                    throw new CallExecutorException(response.code() + " " + response.message());
                }
            } catch (CallExecutorException | IOException e) {
                Log.e(NAME, "Execute error", e);
            }
        });

        thread.start();
    }
}
