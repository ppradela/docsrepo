package docsrepo.ejb;

import docsrepo.ejb.soap.client.DocsService;
import docsrepo.ejb.soap.client.DocsWebService;
import docsrepo.ejb.soap.client.Document;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;

@Stateless
@LocalBean
public class DocsServiceBean {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/docsrepo-soap/DocsService?wsdl")
    private DocsService service;

    public void addDocument(String name, String contentDescription, byte[] file) {

        try { // Call Web Service Operation
            DocsWebService port = service.getDocsWebServicePort();
            Document document = new Document();
            document.setName(name);
            document.setContentDescription(contentDescription);
            document.setFile(file);
            port.createDocument(document);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void updateDocument(int id, String name, String contentDescription, byte[] file) {

        try { // Call Web Service Operation
            DocsWebService port = service.getDocsWebServicePort();
            Document document = port.getDocument(id);
            if (!name.isEmpty() && !name.equals(document.getName())) {
                document.setName(name);
                port.updateDocument(document);
            }
            if (!contentDescription.isEmpty() && !contentDescription.equals(document.getContentDescription())) {
                document.setContentDescription(contentDescription);
                port.updateDocument(document);
            }
            if (file != null) {
                document.setFile(file);
                port.updateDocument(document);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void deleteDocument(int id) {

        try { // Call Web Service Operation
            DocsWebService port = service.getDocsWebServicePort();
            port.removeDocument(id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public Document getDocument(int id) {

        Document document = null;
        try { // Call Web Service Operation
            DocsWebService port = service.getDocsWebServicePort();
            Document result = port.getDocument(id);
            document = result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return document;
    }

    public byte[] downloadDocFile(int documentId) {

        byte[] file = null;
        try { // Call Web Service Operation
            DocsWebService port = service.getDocsWebServicePort();
            int id = documentId;
            Document result = port.getDocument(id);
            file = result.getFile();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return file;
    }

    public List<Document> getDocuments() {

        List<Document> documents = null;
        try { // Call Web Service Operation
            DocsWebService port = service.getDocsWebServicePort();
            List<Document> result = port.getDocuments();
            documents = result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return documents;
    }
}
