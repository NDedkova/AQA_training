package utilsAPI;

import endpoints.EndPointPetStore;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class PetShopApiSpecification {

    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(EndPointPetStore.BASE_URI)
                .setBasePath(EndPointPetStore.PET)
                .setContentType(ContentType.JSON)
                .log(LogDetail.METHOD)
                .log(LogDetail.URI)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .build();
    }
}
