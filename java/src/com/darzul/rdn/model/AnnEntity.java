package com.darzul.rdn.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by DarzuL on 9/20/15.
 */
public interface AnnEntity {

    void addInputs(float[] newInputs);

    List<Float> getInputs();

    String getOutputs();
}
