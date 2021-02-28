package br.com.projeto.brprev.errors;

public class ResourceNotFoundDetails extends ErrorDetails{

    public static final class Builder {
        private String title;
        private int status;
        private String datail;
        private long timestamp;
        private String developerMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder datail(String datail) {
            this.datail = datail;
            return this;
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ResourceNotFoundDetails build() {
            ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
            resourceNotFoundDetails.setDeveloperMessage(developerMessage);
            resourceNotFoundDetails.setDatail(datail);
            resourceNotFoundDetails.setTitle(title);
            resourceNotFoundDetails.setTimestamp(timestamp);
            resourceNotFoundDetails.setStatus(status);
            return resourceNotFoundDetails;
        }
    }
}
