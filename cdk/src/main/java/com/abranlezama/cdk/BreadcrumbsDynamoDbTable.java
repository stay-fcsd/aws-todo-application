package com.abranlezama.cdk;

import dev.stratospheric.cdk.ApplicationEnvironment;
import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.services.dynamodb.*;
import software.constructs.Construct;

public class BreadcrumbsDynamoDbTable extends Construct {

    public BreadcrumbsDynamoDbTable(
            final Construct scope,
            final String id,
            final ApplicationEnvironment applicationEnvironment,
            final InputParameter inputParameters
            ) {
        super(scope, id);

        new Table(
                this,
                "BreadcrumbsDynamoDbTable",
                TableProps.builder()
                        .partitionKey(
                                Attribute.builder().type(AttributeType.STRING).name("id").build())
                        .tableName(applicationEnvironment.prefix(inputParameters.tableName))
                        .encryption(TableEncryption.AWS_MANAGED)
                        .billingMode(BillingMode.PROVISIONED)
                        .readCapacity(10)
                        .writeCapacity(10)
                        .removalPolicy(RemovalPolicy.DESTROY)
                        .build());
    }

    record InputParameter(String tableName) {}
}
