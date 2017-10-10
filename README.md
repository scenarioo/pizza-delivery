# pizza-delivery

Simple Selenium and Scenarioo Demo in Java.

This example demonstrates how to use the Scenarioo Writer Library in your Selenium Webtests to produce Scenarioo Documentation data.

## Production code

The production code is a very simple pizza ordering web app without a dynamic backend. It's all in the folder `pizza-shop`.

If you can't wait to see the pizza ordering awesomeness, look [here](http://scenarioo.github.io/pizza-delivery/) ;-)

Usage hint: The only known phone number is 0791111111, all other phone numbers require you to enter a new delivery address.

## Test code

The test code is based on Java using Selenium for the Browser automatisation and Scenarioo for documenting the test cases. You'll find this code in the folder `test`.


## Windows setup

Execute the following powershell commands

```
Add-Type -AssemblyName System.IO.Compression.FileSystem
function Unzip
{
    param([string]$zipfile, [string]$outpath)
    [System.IO.Compression.ZipFile]::ExtractToDirectory($zipfile, $outpath)
}

# https://download.mozilla.org/?product=firefox-51.0.1-SSL&os=win64&lang=de
wget "http://www.pirbot.com/mirrors/apache/tomcat/tomcat-8/v8.5.11/bin/apache-tomcat-8.5.11-windows-x64.zip" -outfile "tomcat.zip"
wget "https://github.com/scenarioo/scenarioo/releases/download/3.0.0/scenarioo-3.0.0.war" -outfile "scenarioo.war"
wget "https://raw.githubusercontent.com/scenarioo/scenarioo/develop/scenarioo-server/src/main/resources/config.xml" -outfile "config.xml"

Expand-Archive -LiteralPath "tomcat.zip" -DestinationPath "apache-tomcat"
Move-Item ".\apache-tomcat\apache-tomcat-8.5.11\*" ".\apache-tomcat"
Move-Item ".\scenarioo.war" ".\apache-tomcat\webapps"

Write-Host Copying config to $env:USERPROFILE\.scenarioo, please update path.
New-Item -ItemType directory -Path $env:USERPROFILE\.scenarioo
Move-Item ".\config.xml" "$env:USERPROFILE\.scenarioo"

git clone https://github.com/scenarioo/pizza-delivery.git
cd pizza-delivery\test
#.\gradlew test
```
