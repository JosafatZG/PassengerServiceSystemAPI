package com.passengerservicesystemapi.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serial;
import java.io.Serializable;

public class LogTrace implements Serializable {

    @Serial
    private static final long serialVersionUID = 1230685799899335071L;
    private Logger log = null;
    private StringBuffer bufer = null;

    public LogTrace() {
        super();

        this.setLog(LogManager.getLogger(this.getClass()));
        this.setBufer(new StringBuffer());
    }

    protected void instantLog(String mensaje) {
        this.instantLog(mensaje, null, null);
    }

    protected void instantLogId(String mensaje, String id) {
        this.instantLog(mensaje, id, null);
    }

    protected void instantLogFidusl(String mensaje, String id) {
        this.instantLog(mensaje, id, "Y");
    }

    protected void instantLog(String mensaje, String id, String salto) {
        StringBuffer logBufToPrint = new StringBuffer();
        try {
            if (id == null) {
                logBufToPrint = this.log(mensaje, null, logBufToPrint);
            } else {
                logBufToPrint = this.log(mensaje, id, logBufToPrint);

                if (salto != null) {
                    logBufToPrint.append("\n");
                }
            }
            this.printLog(logBufToPrint);
        } catch (Exception e) {
            System.out.println("Problemas para logear mensaje [id: " + id + "]  [" + mensaje + "] $$$$$");
        }
    }

    protected StringBuffer log(String mensaje, String id, StringBuffer logBuf) {
        if (logBuf == null)
            logBuf = new StringBuffer();
        if (id == null) {
            logBuf.append(" ").append(mensaje);
        } else {
            //logBuf.append("\n");
            logBuf.append("[id:");
            logBuf.append(id);
            logBuf.append("] ");
            logBuf.append(" ").append(mensaje);
        }
        return logBuf;

    }

    protected void printLog(StringBuffer logBuf) {
        StringBuffer logBufToPrint;

        if (logBuf == null) {
            logBufToPrint = this.getBufer();
        } else {
            logBufToPrint = logBuf;
        }
        // imprimimos a consola
        if ((logBufToPrint != null) && (logBufToPrint.length() > 1))
            if (AppMicroPSSConstants.INFO.equalsIgnoreCase(AppMicroPSSConstants.INFO))
                this.getLog().info(logBufToPrint.toString());
            else if (AppMicroPSSConstants.INFO.equalsIgnoreCase(AppMicroPSSConstants.ERROR))
                this.getLog().error(logBufToPrint.toString());
            else if (AppMicroPSSConstants.INFO.equalsIgnoreCase(AppMicroPSSConstants.DEBUG))
                this.getLog().debug(logBufToPrint.toString());
            else if (AppMicroPSSConstants.INFO.equalsIgnoreCase(AppMicroPSSConstants.WARN))
                this.getLog().warn(logBufToPrint.toString());
            else if (AppMicroPSSConstants.INFO.equalsIgnoreCase(AppMicroPSSConstants.TRACE))
                this.getLog().trace(logBufToPrint.toString());
            else if (AppMicroPSSConstants.INFO.equalsIgnoreCase(AppMicroPSSConstants.FATAL))
                this.getLog().fatal(logBufToPrint.toString());
            else
                this.getLog().info(logBufToPrint.toString());

        // limpiamos el bufer
        logBufToPrint = new StringBuffer();
        if (logBuf == null) {
            this.setBufer(logBufToPrint);
        }
    }

    public StringBuffer getBufer() {
        if (bufer == null) {
            bufer = new StringBuffer();
        }
        return bufer;
    }

    public void setBufer(StringBuffer bufer) {
        this.bufer = bufer;
    }

    public Logger getLog() {
        return log;
    }

    public void setLog(Logger log) {
        this.log = log;
    }
}
