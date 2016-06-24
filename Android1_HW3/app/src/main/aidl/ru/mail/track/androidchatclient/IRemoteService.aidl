package ru.mail.track.androidchatclient;

interface IRemoteService {
    oneway void sendMessage(String message);
}