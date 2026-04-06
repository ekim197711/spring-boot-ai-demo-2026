# spring-boot-ai-demo-2026

# Azure and tofu

az ad sp create-for-rbac --name "TofuServicePrincipal" --role Contributor --scopes /subscriptions/<subscription_id>
set these environment variables:

ARM_CLIENT_ID = "<appId>"
ARM_CLIENT_SECRET = "<password>"
ARM_SUBSCRIPTION_ID = "<subscriptionId>"
ARM_TENANT_ID = "<tenantId>"
