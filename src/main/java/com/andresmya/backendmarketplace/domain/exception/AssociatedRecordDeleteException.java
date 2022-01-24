package com.andresmya.backendmarketplace.domain.exception;

public class AssociatedRecordDeleteException extends Exception{
    public AssociatedRecordDeleteException(String resource, String associatedRecord){
        super("Resource " + resource + " can't be deleted because has following associated record(s): " + associatedRecord);
    }
}
