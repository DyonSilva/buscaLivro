package com.dyon.projeto.literatura.util;

import com.dyon.projeto.literatura.model.LivroRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JsonParser {

    private ObjectMapper objectMapper = new ObjectMapper();


    public LivroRecord parsearLibro(String json) {
        try {
            return objectMapper.readValue(json, LivroRecord.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public List<LivroRecord> parsearLivros(String json) {
        List<LivroRecord> lista = new ArrayList<>();
        try {

            JsonNode jsonObject = objectMapper.readTree(json);
            JsonNode resultados = jsonObject.get("results");

            for (JsonNode node : (ArrayNode) resultados) {
                LivroRecord libro = objectMapper.treeToValue(node, LivroRecord.class);
                lista.add(libro);
            }

            return lista;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
