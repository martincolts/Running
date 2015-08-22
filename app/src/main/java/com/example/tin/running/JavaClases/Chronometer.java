package com.example.tin.running.JavaClases;

/**
 * Created by Tin on 16/08/2015.
 */
public final class Chronometer{
    private long begin, end;

    public void start(){
        begin = System.currentTimeMillis();
    }

    public void stop(){
        end = System.currentTimeMillis();
    }

    public long getTimeStoped () { return end-begin;}

    public long getTime() {
        return System.currentTimeMillis()-begin;
    }

    public long getMilliseconds() {
        return getTime();
    }

    public double getSeconds() {
        return (getTime()) / 1000.0;
    }

    public double getMinutes() {
        return ((getTime()) / 60000);
    }

    public double getHours() {
        return (getTime()) / 3600000.0;
    }

    public String getTimeFormated (){
        //ver bien este calculo
        String s = new String ();
        Double minutes = getMinutes();
        Long minutos = minutes.longValue();
        Double seconds = getSeconds();
        Long segundos ;
        Long miliseconds = new Long(0);


        if (minutos != 0)
            segundos = seconds.longValue()%60;
        else
            segundos = seconds.longValue();

        if (seconds != 0)
            miliseconds = getMilliseconds()%1000;
        else
            miliseconds = getMilliseconds();

        String min = null;
        if ( (minutos < 10) && (minutos > 0))
            min = "0"+minutos.toString() ;
        else
            min = minutos.toString() ;

        String seg = null;
        if ( (segundos < 10) && (segundos > 0))
            seg = "0"+segundos.toString() ;
        else
            seg = segundos.toString() ;

        String milisegundos = miliseconds.toString();
        String mili ;
        if (milisegundos.length() == 1){
            mili = "00"+milisegundos ;
            s += min+":"+seg+"."+mili;
            return s ;
        }
        else
        if (milisegundos.length() == 2){
            mili = "0"+milisegundos ;
            s += min+":"+seg+"."+mili;
            return s ;
        }

        s += min+":"+segundos+"."+milisegundos;
        return s ;
    }

}