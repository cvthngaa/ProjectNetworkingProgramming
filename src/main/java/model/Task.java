package model;

public class Task {
    private int userId;
    private String originalText;
    private String sourceLanguage;
    private String targetLanguage;
    private String status;
    private String result;

    public Task(int userId, String originalText, String sourceLanguage, String targetLanguage, String status, String result) {
        this.userId = userId;
        this.originalText = originalText;
        this.sourceLanguage = sourceLanguage;
        this.targetLanguage = targetLanguage;
        this.status = status;
        this.result = result;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Task [userId=" + userId + ", originalText=" + originalText + ", sourceLanguage=" + sourceLanguage 
                + ", targetLanguage=" + targetLanguage + ", status=" + status + ", result=" + result + "]";
    }
}
