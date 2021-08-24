package apps.paquete.andres.operat11;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;


//se crea el contexto para compartir a la clase
public class TemplatePDF extends FullActivity {


    private static final int STORAGE_CODE = 1000;
    //variables
    private Context context;
    private File pdfFile;
    private Document document;
    private PdfWriter pdfWriter;
    private Paragraph paragraph;

    //variables para la fuente del texto
    private Font fTitle = new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLD);
    private Font fSubTitle = new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD);
    private Font fText = new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD);
    private Font fTextTable = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
    private Font fTextTable1 = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.ITALIC);
    private Font fTextTable2 = new Font(Font.FontFamily.HELVETICA,8,Font.NORMAL);
    private Font fHighText = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD, BaseColor.RED);


    public TemplatePDF(Context context) {

        this.context = context;
    }

    //metodo para abrir el documento
    public void openDocument(){
        createFile();//llama el metodo para crear el archivo
            //permiso();
        try {
            //este codigo crea el documento y el tamaño de la hoja

            document = new Document(PageSize.A4);

            //se especifica que se va ha escribir en el documento
            pdfWriter = PdfWriter.getInstance(document,new FileOutputStream(pdfFile));
            document.open();

        }catch (Exception e){
            Log.e("openDocument",e.toString());
        }



    }

    //se crea un archivo PDF en el dispositivo movil
    private void createFile(){

        //codigo para crear la carpeta
        File folder = new File(Environment.getExternalStorageDirectory().toString(),"PDF_OpraT");
        if (!folder.exists())
            folder.mkdirs();
        //codigo para crear el archivo PDF
        pdfFile = new File(folder, FullActivity.selRegistro + ".pdf");


    }





    //metodo para cerrar el documento
    public void closeDocument(){
        document.close();
    }

    //metadatos del archivo pdf.. son las propiedades
    public void addMetadata(String title, String subject, String author){
        document.addTitle(title);
        document.addSubject(subject);
        document.addAuthor(author);
    }
//metodo para insertar tutulo / subtitulo y fecha en la que se genero el documento PDF
    public void addTitles(String title, String subTitle, String date){
        //este codigo muestra la fecha en la que se genero el archivo
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        month = month + 1;
        String fecha = twoDigits(day) + "/" + twoDigits(month) + "/" + year;
        //variables para insertar
        //date = fecha;
        //subTitle = FullActivity.selRegistro;
        //title = "Bitacora de Vuelo";
        //codigo para insertar el el documento PDF el titulo Subtitulo y fecha
       try {
           paragraph = new Paragraph();
           addChildP(new Paragraph(title, fTitle));
           addChildP(new Paragraph("Registro No: " + subTitle, fSubTitle));
           addChildP(new Paragraph("Generado: " + date, fHighText));
           paragraph.setSpacingAfter(30);
           document.add(paragraph);
       }catch (Exception e){
           Log.e("addTitles",e.toString());
       }



    }
    /////////////////////////////////////////
    //metodo para insertar tutulo / subtitulo y fecha en la que se genero el documento PDF
    public void addDatos(String numFac, String matriculaHK, String tipo){


        try {
            paragraph = new Paragraph();
            addChildPjustifie(new Paragraph("Avion: " + tipo));
            addChildPjustifie(new Paragraph(numFac));
            addChildPjustifie(new Paragraph(matriculaHK));
            paragraph.setSpacingAfter(20);
            document.add(paragraph);
        }catch (Exception e){
            Log.e("addDatos",e.toString());
        }



    }
    /////////////////////////////////////////

    public void addChildP(Paragraph childParagraph){
        childParagraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(childParagraph);

    }

    public void addChildPjustifie(Paragraph childParagraph){
        childParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
        paragraph.add(childParagraph);

    }

    public void addParagraph(String text){
        try{
            paragraph = new Paragraph(text,fText);
            paragraph.setSpacingAfter(2);
            paragraph.setSpacingBefore(5);
            document.add(paragraph);
        }catch (Exception e){
            Log.e("addParagraph",e.toString());
        }

    }

    public void addParagraph1(String text){
        try{
            paragraph = new Paragraph(text,fText);
            paragraph.setSpacingAfter(2);
            paragraph.setSpacingBefore(50);
            document.add(paragraph);
        }catch (Exception e){
            Log.e("addParagraph",e.toString());
        }

    }
//crear tabla
public void createTable(String[]header, ArrayList<String[]>clients){

    try{
        paragraph = new Paragraph();
        paragraph.setFont(fText);
        PdfPTable pdfPTable = new PdfPTable(header.length);
        pdfPTable.setWidthPercentage(110);
        pdfPTable.setSpacingBefore(10);
        PdfPCell pdfPCell;
        int indexC = 0;
        //crea el encabezado
        while (indexC < header.length){
            pdfPCell = new PdfPCell(new Phrase(header[indexC++],fTextTable));
            pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pdfPTable.addCell(pdfPCell);
        }

        //crea las filas
        for (int indexR=0; indexR < clients.size(); indexR++){
            String[] row = clients.get(indexR);
            //agrega las celdas dependiendo de la columna
            for (indexC=0; indexC < header.length; indexC++){
                pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setFixedHeight(20);
                pdfPTable.addCell(pdfPCell);
            }
        }

        paragraph.add(pdfPTable);
        document.add(paragraph);

    }catch (Exception e){
        Log.e("createTableVuelos",e.toString());
    }

}


/////////////////
//crear tabla para los totales
public void createTableTotales(String[]header, ArrayList<String[]>clients){

    try{
        paragraph = new Paragraph();
        paragraph.setFont(fTextTable1);
        PdfPTable pdfPTable = new PdfPTable(header.length);
        pdfPTable.setWidthPercentage(90);
        pdfPTable.setSpacingBefore(8);
        PdfPCell pdfPCell;
        int indexC = 0;
        //crea el encabezado
        while (indexC < header.length){
            pdfPCell = new PdfPCell(new Phrase(header[indexC++],fTextTable1));
            pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPCell.setBackgroundColor(BaseColor.GRAY);
            pdfPTable.addCell(pdfPCell);
        }

        //crea las filas
        for (int indexR=0; indexR < clients.size(); indexR++){
            String[] row = clients.get(indexR);
            //agrega las celdas dependiendo de la columna
            for (indexC=0; indexC < header.length; indexC++){
                pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setFixedHeight(20);
                pdfPTable.addCell(pdfPCell);
            }
        }

        paragraph.add(pdfPTable);
        document.add(paragraph);

    }catch (Exception e){
        Log.e("createTableTotales",e.toString());
    }

}
    ////////////////
//crear tabla para EL REGISTRO DE COMBUSTIBLE
    public void createTableFuel(String[]header, ArrayList<String[]>clients){

        try{
            paragraph = new Paragraph();
            paragraph.setFont(fTextTable2);
            PdfPTable pdfPTable = new PdfPTable(header.length);
            pdfPTable.setWidthPercentage(110);
            pdfPTable.setSpacingBefore(10);
            pdfPTable.setSpacingAfter(30);
            PdfPCell pdfPCell;
            int indexC = 0;
            //crea el encabezado
            while (indexC < header.length){
                pdfPCell = new PdfPCell(new Phrase(header[indexC++],fTextTable2));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setBackgroundColor(BaseColor.GREEN);
                pdfPTable.addCell(pdfPCell);
            }

            //crea las filas
            for (int indexR=0; indexR < clients.size(); indexR++){
                String[] row = clients.get(indexR);

                //agrega las celdas dependiendo de la columna
                for (indexC=0; indexC < header.length; indexC++){
                    pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    pdfPCell.setFixedHeight(20);
                    pdfPTable.addCell(pdfPCell);
                }
            }

            paragraph.add(pdfPTable);
            document.add(paragraph);

        }catch (Exception e){
            Log.e("createTableFuel",e.toString());
        }

    }
//////////////////////////////



    //este metodo permite ver la fecha en dos digitos 01/01/2018
    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }

////////////////////visualizar PDF////////////////////////////


//codigo para visualizaar el documento el la actividad viewPDF
    public void viewPDF(){
        Intent intent = new Intent(context,ViewPDFActivity.class);
        intent.putExtra("path",pdfFile.getAbsolutePath());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
//codigo para visualizar el documento con un applicacion externa
//codigo para visualizaar el documento el la actividad viewPDF
public void appviewPDF(Activity activity){
        if (pdfFile.exists()) {
            Uri uri = Uri.fromFile(pdfFile);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri,"application/pdf");
            try{
                activity.startActivity(intent);
            }catch (ActivityNotFoundException e){
                activity.startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=com.adobe.reader")));
                Toast.makeText(activity.getApplicationContext(),"No cuenta con aplicacion para PDF",Toast.LENGTH_LONG).show();

            }

        }else {
            Toast.makeText(activity.getApplicationContext(),"No se encontro el Archivo",Toast.LENGTH_LONG).show();
        }
}

}
