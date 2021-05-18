package model;

public interface ModelObserver {
    public void modelChange(String address, String content);
}