package pl.waw.psychologmaja.therapistrelief.entity;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.parameters.P;
import pl.waw.psychologmaja.therapistrelief.entity.Session;

import javax.persistence.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Entity
@Table(name = "invoices")
public class Invoice {

    private final static String COMPANY = "Gabinet Psychoterapeutyczny\nAnna Nowak\n" +
            "ul. Warszawska 15\n00-987 Warszawa";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double payment;
    @Transient
    private Session session;
    @Transient
    private Font mainFont;
    @Transient
    private Font mainFontBold;
    @Transient
    private Font secondaryFont;

    public Invoice() {
    }

    public Invoice(Session session) {
        this.session = session;
        this.payment = session.getPaymentDue();
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    private void initializeFonts() {
        try {
            mainFont = new Font(BaseFont.createFont("/Users/bombadil/Desktop/Git/therapist-relief/src/main/resources/fonts/Courier-New.ttf", BaseFont.IDENTITY_H, true), 16);
            mainFontBold = new Font(BaseFont.createFont("/Users/bombadil/Desktop/Git/therapist-relief/src/main/resources/fonts/Courier-New-Bold.ttf", BaseFont.IDENTITY_H, true), 16);
            secondaryFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, "utf-8", 16, Font.BOLD, BaseColor.BLACK);
        } catch (DocumentException de) {
            de.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void addDocumentHeader(Document document) throws DocumentException, IOException {
        Paragraph paragraph = new Paragraph();
        Paragraph left = new Paragraph(COMPANY, mainFont);
        left.setAlignment(Element.ALIGN_LEFT);
        paragraph.add(left);
        Chunk glue = new Chunk(new VerticalPositionMark());
        paragraph.add(glue);
        Paragraph right = new Paragraph("Invoice no " + id + "\n" + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), mainFont);
        right.setAlignment(Element.ALIGN_RIGHT);
        paragraph.add(right);
        left = new Paragraph("Bill to: \n" + session.getPatients().get(0).getFullName() + "\n" +
                session.getPatients().get(0).getEmail(), mainFont);
        left.setAlignment(Element.ALIGN_LEFT);
        paragraph.add(left);
        addEmptyLines(paragraph, 2);
        document.add(paragraph);

    }

    private void addTableHeader(PdfPTable pdfPTable) throws IOException, DocumentException {
        String[] names = {"DESCRIPTION", "AMOUNT"};
        IntStream.range(0, names.length)
                .forEach(
                        i -> {
                            PdfPCell pdfPCell = new PdfPCell();
                            pdfPCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            pdfPCell.setBorderWidth(2);
                            Phrase phrase = new Phrase(names[i], mainFont);
                            if (i == 0) {
                                pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            } else {
                                pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            }
                            pdfPCell.setPhrase(phrase);
                            pdfPTable.addCell(pdfPCell);
                        }
                );
    }

    private void addTableRows(PdfPTable pdfPTable) {
        String[] names = {"Therapeutic session", String.valueOf(session.getPaymentDue())};
        IntStream.range(0, names.length)
                .forEach(
                        i -> {
                            PdfPCell pdfPCell = new PdfPCell();
                            pdfPCell.setBackgroundColor(BaseColor.WHITE);
                            pdfPCell.setBorderWidth(1);
                            pdfPCell.setFixedHeight(160f);
                            Phrase phrase = new Phrase(names[i], mainFont);
                            if (i == 0) {
                                pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            } else {
                                pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            }
                            pdfPCell.setPhrase(phrase);
                            pdfPTable.addCell(pdfPCell);
                        }
                );
    }

    private void addTableSummary(PdfPTable pdfPTable) {
        String[] names = {"Total:", String.valueOf(session.getPaymentDue())};
        IntStream.range(0, names.length)
                .forEach(
                        i -> {
                            PdfPCell pdfPCell = new PdfPCell();
                            pdfPCell.setBackgroundColor(BaseColor.WHITE);
                            pdfPCell.setBorderWidth(1);
                            Phrase phrase = new Phrase(names[i], mainFont);
                            if (i == 0) {
                                pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            } else {
                                pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                pdfPCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            }
                            pdfPCell.setPhrase(phrase);
                            pdfPTable.addCell(pdfPCell);
                        }
                );
    }

    private void addTable(Document document) throws DocumentException, IOException {
        PdfPTable pdfPTable = new PdfPTable(2);
        addTableHeader(pdfPTable);
        addTableRows(pdfPTable);
        addTableSummary(pdfPTable);
        document.add(pdfPTable);
    }

    private void addDocumentFooter(Document document) {

    }

    public void get(HttpServletResponse response) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        initializeFonts();
        document.setPageSize(PageSize.A4);

        document.open();

        addDocumentHeader(document);
        addTable(document);
        addDocumentFooter(document);

        document.close();
    }

    private void addEmptyLines(Paragraph paragraph, int lines) {
        for (int i = 0; i < lines; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}
