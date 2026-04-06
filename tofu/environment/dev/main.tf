terraform {
  backend "azurerm" {
    storage_account_name = "mikesstoragedemopurpose"
    container_name       = "tfstate-datafactory-demo"
    key                  = "dev.terraform.tfstate"
    use_azuread_auth     = false # Required to use CLI/Identity instead of keys
    resource_group_name  = "my_ressource_group"
  }
}
module "datafactory_and_stuff" {
  source         = "../../module/datafactory"
  resource_group = "my_ressource_group"
  location       = "East US"
  environment    = "dev"
}

