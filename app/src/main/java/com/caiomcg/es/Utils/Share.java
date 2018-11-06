package com.caiomcg.es.Utils;

import android.content.Intent;

public class Share {
    public static Intent sendText(String body) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        intent.putExtra(Intent.EXTRA_SUBJECT, "AgroTec");
        intent.putExtra(Intent.EXTRA_TEXT, String.format("Agrotec\n\n%s",body));
        intent.setType("text/plain");
        return Intent.createChooser(intent, "AgroTec - Agricultura e tecnologia");
    }
}
