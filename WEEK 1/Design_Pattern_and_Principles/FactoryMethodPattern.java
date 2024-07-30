import java.util.Scanner;

public class FactoryMethodPattern {

    // Document interface
    public interface Document {
        void open();
        void close();
    }

    // Concrete Document Classes
    public static class WordDocument implements Document {
        @Override
        public void open() {
            System.out.println("Opening Word Document.");
        }

        @Override
        public void close() {
            System.out.println("Closing Word Document.");
        }
    }

    public static class PdfDocument implements Document {
        @Override
        public void open() {
            System.out.println("Opening PDF Document.");
        }

        @Override
        public void close() {
            System.out.println("Closing PDF Document.");
        }
    }

    public static class ExcelDocument implements Document {
        @Override
        public void open() {
            System.out.println("Opening Excel Document.");
        }

        @Override
        public void close() {
            System.out.println("Closing Excel Document.");
        }
    }

    // DocumentFactory abstract class
    public abstract static class DocumentFactory {
        public abstract Document createDocument();
    }

    // Concrete Factory Classes
    public static class WordDocumentFactory extends DocumentFactory {
        @Override
        public Document createDocument() {
            return new WordDocument();
        }
    }

    public static class PdfDocumentFactory extends DocumentFactory {
        @Override
        public Document createDocument() {
            return new PdfDocument();
        }
    }

    public static class ExcelDocumentFactory extends DocumentFactory {
        @Override
        public Document createDocument() {
            return new ExcelDocument();
        }
    }

    // Test class
    public static class FactoryMethodTest {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // Prompt user for document type
            System.out.println("Enter the type of document to create (Word, PDF, Excel):");
            String documentType = scanner.nextLine().trim().toLowerCase();

            DocumentFactory factory = null;

            // Determine which factory to use based on user input
            switch (documentType) {
                case "word":
                    factory = new WordDocumentFactory();
                    break;
                case "pdf":
                    factory = new PdfDocumentFactory();
                    break;
                case "excel":
                    factory = new ExcelDocumentFactory();
                    break;
                default:
                    System.out.println("Invalid document type.");
                    System.exit(1);
            }

            // Create and use the document
            Document document = factory.createDocument();
            document.open();
            document.close();

            scanner.close();
        }
    }
}
