package pl.edu.vistula.firstrestapispring.firstrestapispring.product.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public class UpdateProductRequest extends ProductRequest {
    @JsonCreator
    public UpdateProductRequest(String name) {
        super(name);
    }
}