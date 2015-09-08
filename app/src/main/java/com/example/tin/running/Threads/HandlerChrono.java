package com.example.tin.running.Threads;

        import android.os.Handler;
        import android.os.Message;
        import android.widget.TextView;

public class HandlerChrono extends Handler
{
    String cron;
    TextView tiempo;

    public HandlerChrono(TextView t)
    {
        tiempo = t;
    }
    public void handleMessage(Message msg)
    {
        tiempo.setText(cron);
    }
    public void setCron(String hcron) {
        this.cron = hcron;
    }
    public void act()
    {
        super.sendEmptyMessage(0);
    }
}