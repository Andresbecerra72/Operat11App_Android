package apps.paquete.andres.operat11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.andres.operat11.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class ViewPDFActivity extends AppCompatActivity {

    private PDFView pdfView;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);

        //codigo paraver el documento PDF
        pdfView = (PDFView)findViewById(R.id.pdfView);

        //codigo para recibir el archivo PDF
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            file = new File(bundle.getString("path",""));
                    }
        pdfView.fromFile(file) //caracteristicas del archivo
                .enableSwipe(true)// cambios de pagina
                .swipeHorizontal(true)//vertical -horizontal
                .enableDoubletap(true)
                .enableAntialiasing(true)//mejora la resolucion
                .load();



    }
}
