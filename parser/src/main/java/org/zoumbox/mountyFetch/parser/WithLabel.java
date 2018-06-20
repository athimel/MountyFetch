package org.zoumbox.mountyFetch.parser;

/**
 * Interface qui permet d'uniformiser les enum dont le name() n'est pas utilisable en pretty name et qui ont donc
 * recours à un label
 */
public interface WithLabel {

    String getLabel();

}
