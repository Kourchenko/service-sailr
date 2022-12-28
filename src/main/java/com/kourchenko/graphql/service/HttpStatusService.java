package com.kourchenko.graphql.service;

import java.net.URL;
import java.io.IOException;
import java.net.HttpURLConnection;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.kourchenko.mongodb.dao.AccountHttpStatus;

@Service
public class HttpStatusService {

    public HttpStatus getHttpStatus(String websiteURL) {
        try {
            URL url = new URL(websiteURL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            int responseCode = connection.getResponseCode();
            HttpStatus status = HttpStatus.valueOf(responseCode);

            return status;
        } catch (IOException e) {
            // Logger
            return HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public AccountHttpStatus getAccountHttpStatus(String websiteURL) {
        AccountHttpStatus accountHttpStatus = null;

        try {
            URL url = new URL(websiteURL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            int responseCode = connection.getResponseCode();
            HttpStatus status = HttpStatus.valueOf(responseCode);

            accountHttpStatus = new AccountHttpStatus(
                status.is2xxSuccessful(),
                status.value(),
                status.getReasonPhrase());
        } catch (IOException e) {
            // Logger
            accountHttpStatus = new AccountHttpStatus(
                false,
                HttpStatus.BAD_REQUEST.value(),
                e.getClass().getSimpleName());
        } catch (Exception e) {
            accountHttpStatus = new AccountHttpStatus(
                false,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getClass().getSimpleName());
        }

        return accountHttpStatus;

    }

}
