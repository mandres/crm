package py.com.quality.utiles;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Util {

    public static java.util.List<java.util.Date> getListaEntreFechas(java.util.Date fechaInicio, java.util.Date fechaFin) {
        // Convertimos la fecha a Calendar, mucho más cómodo para realizar
        // operaciones a las fechas
        Calendar c1 = Calendar.getInstance();
        c1.setTime(fechaInicio);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(fechaFin);

        // Lista donde se irán almacenando las fechas
        java.util.List<java.util.Date> listaFechas = new java.util.ArrayList<>();

        // Bucle para recorrer el intervalo, en cada paso se le suma un día.
        while (!c1.before(c2)) {
            if (c1.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                listaFechas.add(c1.getTime());
            }
            c1.add(Calendar.DAY_OF_MONTH, -1);
        }
        return listaFechas;
    }

    public int getDiasHabiles(Calendar fechaInicial, Calendar fechaFinal) {
        int diffDays = 0;
        //mientras la fecha inicial sea menor o igual que la fecha final se cuentan los dias
        while (fechaInicial.before(fechaFinal) || fechaInicial.equals(fechaFinal)) {
            //si el dia de la semana de la fecha minima es diferente de sabado o domingo
            if (fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
                    && fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                //se aumentan los dias de diferencia entre min y max
                diffDays++;
            }
        }
        return diffDays;

    }

    public static long diasTranscurridos(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        java.util.Date utilDate = new java.util.Date();
        try {
            utilDate = sdf.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        //fecha inicio
        Calendar fechaInicio = Calendar.getInstance();
        //fecha fin
        Calendar fechaFin = new GregorianCalendar();
        fechaFin.setTime(utilDate);
        //restamos las fechas como se puede ver son de tipo Calendar,
        //debemos obtener el valor long con getTime.getTime.
        long mil1 = fechaInicio.getTimeInMillis();
        long mil2 = fechaFin.getTimeInMillis();
        long dif = mil1 - mil2;
        long diffDays = dif / (24 * 60 * 60 * 1000);
        return diffDays;
    }

    public static Date fechaSistema() {
        java.util.Date utilDate1 = new java.util.Date();
        long lnMilisegundos = utilDate1.getTime();
        java.sql.Date fechaActual = new java.sql.Date(lnMilisegundos);
        return fechaActual;
    }

    public static final String BD_EMPRESAS = "quality_mkt";
    public static final int REGISTROS_PAGINA = 10;

    public static java.sql.Date utilToSqlDate(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }

    public static java.util.Date sqlToUtilDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }

    public static java.util.Date stringToUtilDate(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = new java.util.Date();
        try {
            utilDate = sdf.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return utilDate;
    }

    public static java.sql.Date stringToSqlDate(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = null;
        try {
            utilDate = sdf.parse(fecha);
            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sqlDate;
    }

    public static String sqlDateToString(java.sql.Date fecha) {
        String string;
        Calendar calendar = GregorianCalendar.getInstance();
        if (fecha == null) {
            return null;
        } else {
            calendar.setTime(fecha);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            string = sdf.format(fecha);
            return string;
        }
    }

    public static Timestamp stringToUtilTimestamp(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
        java.util.Date today = new java.util.Date();
        try {
            today = sdf.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Timestamp ts1 = new java.sql.Timestamp(today.getTime());
        SimpleDateFormat otroformato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        otroformato.format(ts1);
        return ts1;
    }

    public static String sqlTimestampToString(Timestamp fecha) {
        String string;
        Calendar calendar = GregorianCalendar.getInstance();
        if (fecha == null) {
            return null;
        } else {
            calendar.setTime(fecha);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            string = sdf.format(fecha);
            return string;
        }
    }

    public static String espacios(int longitud) {
        longitud = longitud * 3;
        String valor = "";
        for (int i = 1; i <= longitud; i++) {
            valor += "&nbsp;";
        }
        return valor;
    }

    public static String darFormatoDouble(Double numero) {
        DecimalFormat formateador = new DecimalFormat("###,###,###.##");
        formateador.setMaximumFractionDigits(0);
        return formateador.format(numero);
    }

    public static String darFormato(float numero) {
        DecimalFormat formateador = new DecimalFormat("###,###,###.##");
        formateador.setMaximumFractionDigits(2);
        return formateador.format(numero);
    }

    public static String darFormatoInt(int numero) {
        DecimalFormat formateador = new DecimalFormat("###,###,###");
        formateador.setMaximumFractionDigits(2);
        return formateador.format(numero);
    }

    public static String quitarGuiones(String texto) {
        return texto.replace("--", "");
    }

    public static String md5(String palabra) {
        String palabraMD5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(palabra.getBytes());
            int size = b.length;
            StringBuffer sb = new StringBuffer(size);
            for (int i = 0; i < size; i++) {
                int u = b[i] & 255;
                if (u < 16) {
                    sb.append("0" + Integer.toHexString(u));
                } else {
                    sb.append(Integer.toHexString(u));
                }
            }
            palabraMD5 = sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return palabraMD5;
    }

    public static String getSN(String letra) {
        String valor = "";
        if (letra.equals("S")) {
            valor = "Si";
        } else if (letra.equals("N")) {
            valor = "No";
        }
        return valor;
    }

    public static String getMesString(int mes) {
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre"};
        return meses[mes];
    }

    public static String dmaToAmd(String dma) {
        String valor = dma.substring(6, 10) + "-" + dma.substring(3, 5) + "-" + dma.substring(0, 2);
        return valor;
    }

    public static String AmdTodma(String amd) {
        String valor = amd.substring(8, 10) + "-" + amd.substring(5, 7) + "-" + amd.substring(0, 4);
        return valor;
    }

    public static boolean enviarCorreo(String to, String subject, String mensage) {
        boolean valor = false;
        final String usuario = "sistemamkt@quality.com.py";
        final String clave = "";
        //String to = "edgar.lopez@grupoluminotecnia.com";
        //String subject = "Subject";
        //String mensage = "Mensajes";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", "20.1.1.5");
        props.put("mail.smtp.port", "25");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(usuario, clave);
                    }
                });

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(usuario));
            message.setSubject(subject);

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setContent(mensage, "text/html");
            //message.setText(mensage);

            Transport.send(message);
            System.out.println("Su mensaje ha sido enviado");
            valor = true;
        } catch (MessagingException e) {
            System.out.println("--> " + e.getMessage());
        }
        return valor;
    }

    public void launchFile(File file) {
        if (!Desktop.isDesktopSupported()) {
            return;
        }
        Desktop dt = Desktop.getDesktop();
        try {
            dt.open(file);
        } catch (IOException ex) {
            //this is sometimes necessary with files on other servers ie \\xxx\xxx.xls
            launchFile(file.getPath());
        }
    }

    //this can launch both local and remote files
    public void launchFile(String filePath) {
        if (filePath == null || filePath.trim().length() == 0) {
            return;
        }
        if (!Desktop.isDesktopSupported()) {
            return;
        }
        Desktop dt = Desktop.getDesktop();
        try {
            dt.browse(getFileURI(filePath));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //generate uri according to the filePath
    private URI getFileURI(String filePath) {
        URI uri = null;
        filePath = filePath.trim();
        if (filePath.indexOf("http") == 0 || filePath.indexOf("\\") == 0) {
            if (filePath.indexOf("\\") == 0) {
                filePath = "file:" + filePath;
            }
            try {
                filePath = filePath.replaceAll(" ", "%20");
                URL url = new URL(filePath);
                uri = url.toURI();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        } else {
            File file = new File(filePath);
            uri = file.toURI();
        }
        return uri;
    }

}
