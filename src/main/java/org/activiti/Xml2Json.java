package org.activiti;

import com.fasterxml.jackson.databind.node.ObjectNode;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Xml2Json {

    public static void main(String[] args) throws Exception {
        String filePath = "\\processes\\Developer_Hiring_with_executionListener.bpmn20.xml";
        InputStream bpmnStream = new ByteArrayInputStream(Files.readAllBytes(Paths.get(filePath)));
        XMLInputFactory xif = XMLInputFactory.newInstance();
        InputStreamReader in = new InputStreamReader(bpmnStream, StandardCharsets.UTF_8);
        XMLStreamReader xtr = xif.createXMLStreamReader(in);

        BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);
        BpmnJsonConverter converter = new BpmnJsonConverter();
        ObjectNode editorJsonNode = converter.convertToJson(bpmnModel);
        System.out.println(editorJsonNode);
    }
}
