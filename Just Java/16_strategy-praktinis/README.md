# Strategy design pattern 


Jūsų tikslas - išplėsti klasę lt.techin.strategy.BaseWarehouseTests ir įgyvendinti pateiktus
abstrakčius metodus taip, kad šios klasės testai suveiktų sėkmingai.

Jūs turite parduotuvės produktų sandelį, ir jums reikia rasti produktus pagal gamintoją arba maksimalią kainą.

Turėtumėte įgyvendinti Strategijos dizaino šabloną šiai paieškai. Tam turite FilteringStrategy sąsają.
Paieška naudojant ManufactureFilteringStrategy turėtų ignoruoti didžiąsias ir mažąsias raides, todėl "Samsung" lygu "samsung".


Taip pat reikia sukurti metodą 'List<Product> executeFilteringStrategy(FilteringStrategy filteringStrategy)' klasėje Warehouse, kuris grąžina
produktus pagal jūsų filtravimo strategiją.