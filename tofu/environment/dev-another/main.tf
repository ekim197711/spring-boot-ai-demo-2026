terraform {
  backend "azurerm" {
    storage_account_name = "mikesstoragedemopurpose"
    container_name       = "tfstate-datafactory-demo"
    key                  = "dev.another.terraform.tfstate"
    use_azuread_auth     = false # Required to use CLI/Identity instead of keys
    resource_group_name  = "my_ressource_group"
  }
}
module "another" {
  source = "../../module/another_module"
}

