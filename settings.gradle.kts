rootProject.name = "ecommerce-app"

// Shared libraries
include("libs:common-utils")
include("libs:common-dtos")
include("libs:common-kafka")
include("libs:common-exceptions")

// Microservices
include("services:auth-service")
include("services:order-service")
include("services:pct-service")
include("services:payment-service")
include("services:inventory-service")
include("services:notification-service")
include("services:audit-service")
