package com.proyecto_titulacion.assettrack.util.openpdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.proyecto_titulacion.assettrack.model.dto.MaintenanceActivityDTO;
import com.proyecto_titulacion.assettrack.model.dto.MaintenanceReportDTO;
import com.proyecto_titulacion.assettrack.model.entity.MaintenanceActivity;
import com.proyecto_titulacion.assettrack.model.entity.MaintenanceReport;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Component
public class PreventiveMaintenanceReport {
    public static byte[] generatePdf(MaintenanceReportDTO maintenanceReport) throws DocumentException {
        // Crear un documento PDF
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // Crear el escritor PDF
        PdfWriter.getInstance(document, out);

        // Abrir el documento para agregar contenido
        document.open();


        // Fuentes y estilos
        Font titleFont = new Font(Font.HELVETICA, 16, Font.BOLD);
        Font boldFont = new Font(Font.HELVETICA, 12, Font.BOLD);
        Font regularFont = new Font(Font.HELVETICA, 12);
        Font sectionFont = new Font(Font.HELVETICA, 14, Font.BOLD);

        // Título del informe
        Paragraph title = new Paragraph("REPORTE DE MANTENIMIENTO PREVENTIVO", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        document.add(Chunk.NEWLINE);

        // Información general
        Paragraph infoParagraph = new Paragraph();

        infoParagraph.add(new Chunk("FECHA: ", boldFont));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
        infoParagraph.add(new Phrase(maintenanceReport.date().format(formatter), regularFont));
        infoParagraph.add(Chunk.NEWLINE);

        infoParagraph.add(new Chunk("ACTIVO: ", boldFont));
        infoParagraph.add(new Phrase(maintenanceReport.asset().name(), regularFont));
        infoParagraph.add(Chunk.NEWLINE);

        infoParagraph.add(new Chunk("CÓDIGO DEL ACTIVO: ", boldFont));
        infoParagraph.add(new Phrase(maintenanceReport.asset().serial(), regularFont));
        infoParagraph.add(Chunk.NEWLINE);

        infoParagraph.add(new Chunk("UBICACIÓN: ", boldFont));
        infoParagraph.add(new Phrase(maintenanceReport.asset().location(), regularFont));
        infoParagraph.add(Chunk.NEWLINE);

        infoParagraph.add(new Chunk("RESPONSABLE DE MANTENIMIENTO: ", boldFont));
        infoParagraph.add(new Phrase(maintenanceReport.user().lastName() + " " + maintenanceReport.user().firstName(), regularFont));
        infoParagraph.add(Chunk.NEWLINE);

        infoParagraph.add(new Chunk("TIPO DE MANTENIMIENTO: ", boldFont));
        infoParagraph.add(new Phrase("Preventivo", regularFont));
        infoParagraph.add(Chunk.NEWLINE);

        infoParagraph.add(new Chunk("FRECUENCIA DEL MANTENIMIENTO: ", boldFont));
        infoParagraph.add(new Phrase(maintenanceReport.preventiveMaintenance().frequency(), regularFont));
        infoParagraph.add(Chunk.NEWLINE);

        document.add(infoParagraph);

        document.add(Chunk.NEWLINE);

        // Sección 1: Descripción del Activo
        Paragraph titleSection1 = new Paragraph("1. DESCRIPCIÓN DEL ACTIVO", sectionFont);
        titleSection1.setSpacingAfter(10f);
        document.add(titleSection1);

        Paragraph section1 = new Paragraph();

        section1.add(new Chunk("MARCA/MODELO: ", boldFont));
        section1.add(new Phrase(maintenanceReport.asset().brand() + " / " + maintenanceReport.asset().model(), regularFont));
        section1.add(Chunk.NEWLINE);

        section1.add(new Chunk("FECHA DE INSTALACIÓN: ", boldFont));
        section1.add(new Phrase(maintenanceReport.asset().acquisition().format(formatter), regularFont));
        section1.add(Chunk.NEWLINE);

        section1.add(new Chunk("USO DEL ACTIVO: ", boldFont));
        section1.add(new Phrase(maintenanceReport.asset().description(), regularFont));
        section1.add(Chunk.NEWLINE);

        document.add(section1);

        document.add(Chunk.NEWLINE);


        // Sección 2: Actividades Realizadas
        Paragraph titleSection2 = new Paragraph("2. ACTIVIDADES REALIZADAS", sectionFont);
        titleSection2.setSpacingAfter(10f);
        document.add(titleSection2);

        Paragraph section2 = new Paragraph("Durante el mantenimiento preventivo, se llevaron a cabo las siguientes tareas:", regularFont);
        section2.setSpacingAfter(10f);
        document.add(section2);

        for (int i = 0; i < maintenanceReport.maintenanceActivities().size(); i++) {
            MaintenanceActivityDTO activity = maintenanceReport.maintenanceActivities().get(i);
            document.add(new Paragraph("2." + (i + 1) + " " + activity.name() + ": ", boldFont));

            List taskList = new List(List.UNORDERED);
            taskList.setListSymbol("\u2022");  // Usar el símbolo de viñeta '•'
            taskList.setIndentationLeft(20f);  // Añadir sangría izquierda

            for (String task : activity.tasks()) {
                taskList.add(new ListItem(" " + task));
            }

            document.add(taskList);
        }

        document.add(Chunk.NEWLINE);

        // Sección 3: Observaciones Adicionales
        Paragraph titleSection5 = new Paragraph("3. OBSERVACIONES ADICIONALES", sectionFont);
        titleSection5.setSpacingAfter(10f);
        document.add(titleSection5);

        List observationList = new List(List.UNORDERED);
        observationList.setListSymbol("\u2022");  // Usar el símbolo de viñeta '•'
        observationList.setIndentationLeft(20f);  // Añadir sangría izquierda

        for (String observation : maintenanceReport.observations()) {
            observationList.add(new ListItem(" "+observation));
        }

        document.add(observationList);

        document.add(Chunk.NEWLINE);

        // Sección 4: Recomendaciones
        Paragraph titleSection6 = new Paragraph("4. RECOMENDACIONES", sectionFont);
        titleSection6.setSpacingAfter(10f);
        document.add(titleSection6);

        List recommendationList = new List(List.UNORDERED);
        recommendationList.setListSymbol("\u2022");  // Usar el símbolo de viñeta '•'
        recommendationList.setIndentationLeft(20f);  // Añadir sangría izquierda

        for (String recommendation : maintenanceReport.recommendations()) {
            recommendationList.add(new ListItem(" "+recommendation));
        }

        document.add(recommendationList);

        // Cerrar el documento
        document.close();

        // Retornar el PDF generado como un array de bytes
        return out.toByteArray();
    }
}
