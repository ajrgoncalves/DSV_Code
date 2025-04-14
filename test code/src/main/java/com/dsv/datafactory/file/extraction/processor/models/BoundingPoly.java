package com.dsv.datafactory.file.extraction.processor.models;

import com.dsv.datafactory.model.Vertices;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class BoundingPoly {
    private ArrayList<Vertices> vertices;
    private ArrayList<NormalizedVertices> normalizedVertices;

    public void normalizeVertices(int width, int height) {
        ArrayList<Vertices> normalized = new ArrayList<>();
        for (Vertices vertex : this.vertices) {
            int x = vertex.getX() / width;
            int y = vertex.getY() / height;
            normalized.add(new Vertices(x, y));
        }
    }
}
