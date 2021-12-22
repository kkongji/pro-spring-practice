package com.jun.prospring5.ch03.collection;

public class FtpArtworkSender implements ArtworkSender {
    @Override
    public void sendArtwork(String artworkPath, Recipient recipient) {

    }

    @Override
    public String getFriendlyName() {
        return "파일 전송 프로토콜";
    }

    @Override
    public String getShortName() {
        return "ftp";
    }
}
