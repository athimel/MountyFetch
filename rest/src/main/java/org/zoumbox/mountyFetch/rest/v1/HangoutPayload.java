package org.zoumbox.mountyFetch.rest.v1;

import java.util.Optional;

public class HangoutPayload {

    protected String type;
    protected String token;
    protected String eventTime;
    protected Space space;
    protected Message message;

    public Optional<String> getText() {
        return Optional.ofNullable(message).map(m -> m.text);
    }

    public static final class Space {
        protected String name;
        protected String displayName;
        protected String type;
    }

    public static final class Sender {
        protected String name;
        protected String displayName;
        protected String avatarUrl;
        protected String email;
    }

    public static final class Thread {
        protected String name;
    }

    public static final class Message {
        protected String name;
        protected Sender sender;
        protected String createTime;
        protected String text;
        protected Thread thread;
    }

}
