resource "azurerm_data_factory" "mike" {
  name                = "mikes-datafactory"
  location            = var.location
  resource_group_name = var.resource_group
}

resource "azurerm_data_factory_linked_service_web" "mike" {
  name                = "mike"
  data_factory_id     = azurerm_data_factory.mike.id
  authentication_type = "Anonymous"
  url                 = "https://www.bing.com"
}

resource "azurerm_data_factory_dataset_parquet" "mike" {
  name                = "mike"
  data_factory_id     = azurerm_data_factory.mike.id
  linked_service_name = azurerm_data_factory_linked_service_web.mike.name

  http_server_location {
    relative_url = "http://www.bing.com"
    path         = "foo/bar/"
    filename     = "fizz.txt"
  }
}
