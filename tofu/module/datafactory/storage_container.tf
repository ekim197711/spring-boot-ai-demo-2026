# resource "azurerm_resource_group" "example" {
#   name     = "example-resources"
#   location = "West Europe"
# }

# resource "azurerm_storage_account" "example" {
#   name                     = "examplestoraccount"
#   resource_group_name      = azurerm_resource_group.example.name
#   location                 = azurerm_resource_group.example.location
#   account_tier             = "Standard"
#   account_replication_type = "LRS"
#
#   tags = {
#     environment = "staging"
#   }
# }

resource "azurerm_storage_container" "example" {
  name                  = "blob-input-for-datafactory"
  storage_account_id    = "/subscriptions/d3bbcc96-9035-4825-a177-3b3ba19a693c/resourceGroups/my_ressource_group/providers/Microsoft.Storage/storageAccounts/mikesstoragedemopurpose"
  container_access_type = "private"
}

resource "azurerm_storage_container" "example2" {
  name                  = "blob-input-for-datafactory2"
  storage_account_id    = "/subscriptions/d3bbcc96-9035-4825-a177-3b3ba19a693c/resourceGroups/my_ressource_group/providers/Microsoft.Storage/storageAccounts/mikesstoragedemopurpose"
  container_access_type = "private"
}
#   storage_account_id    = "/subscriptions/d3bbcc96-9035-4825-a177-3b3ba19a693c/resourceGroups/my_ressource_group/providers/Microsoft.Storage/storageAccounts/mikesstoragedemopurpose"
#   container_access_type = "private"
# }
