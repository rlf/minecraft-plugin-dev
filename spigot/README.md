# Installation af en Spigot Server

Først skal man have fat I selve server-filen.
Dette er en jar-fil, og tidligere kunne man bare downloade den.
Desværre gjorde Wolvereness dette umuligt (se [[Wolvereness Episoden]]).

Derfor bliver man nødt til selv at bygge jar-filen.

## Få fat i Spigot og CraftBukkit jar-filerne

Først skal man have downloadet og installeret [Git](https://git-scm.com/downloads) og Java Development Kit (JDK).

Dernæst skal man finde et godt sted på computeren, hvor man vil bygge Spigot, og have sine plugins (f.eks. `C:\Minecraft`).

Via en fil-explorer, åben en `Git Bash` til den nyligt oprettede folder.

Dernæst skal man have downloadet `BuildTools.jar` fra denne side: (https://hub.spigotmc.org/jenkins/job/BuildTools/)
(Højreklik og `Gem i...` den folder hvor `Git Bash` står og venter).

Nu skal vi have bygget vores `spigot.jar`. Det gøres ved at udføre følgende kommando fra `Git Bash`:
```
java -jar BuildTools.jar
```
Dette vil hente Minecraft og alle de filer Bukkit, CraftBukkit og Spigot skal bruge, og dernæst tilpasse diverse filer så man får en krammelækker `spigot.jar` (det tager ret lang tid).

## Installer og konfigurer en spigot-server

Ligesom med `BuildTools.jar`, kan man starte spigot (eller craftbukkit), ved at udføre `java -jar spigot-1.9.4.jar`.

Det vil normalt være en god idé at sætte et batch-script eller lign. op til at starte den med.

F.eks. vil følgende bat-fil starte en spigot server med 4Gb hukkommelse.
```
@echo off
java -Xmx4G -jar spigot-1.9.4.jar
```

**Første gang** serveren starter op, vil den stoppe igen med det samme, efter at have pakket nogle filer og filstrukturer ud.

Den vil skrive noget i retning af dette:
```
[17:14:58 INFO]: Starting minecraft server version 1.9.4
[17:14:58 INFO]: Loading properties
[17:14:58 WARN]: server.properties does not exist
[17:14:58 INFO]: Generating new properties file
[17:14:58 WARN]: Failed to load eula.txt
[17:14:58 INFO]: You need to agree to the EULA in order to run the server. Go to eula.txt for more info.
[17:14:58 INFO]: Stopping server
```
og der vil nu findes en `eula.txt` fil i biblioteket hvorfra jar-filen blev kørt.

Denne fil skal redigeres (brug et hvilken som helst tekst-redigeringsprogram), og accepter EULAen, ved at sætte:
```
eula=true
```
I filen, og starte serveren igen.

I **anden omgang** vil serveren oprette endnu flere foldere, og bl.a. generere div. verdener.
Når den er færdig med at starte, vil den stå i _konsollen_, hvor man så kan indtaste forskellige konsol-kommandoer til selve serveren.

F.eks.
```
stop - stopper serveren igen
op R4zorax - Vil give R4zorax OP status
```
og mange mange flere kommandoer (dem kommer vi til).

Men, TADA - du har nu en helt ren og nyoprettet Spigot-server kørende, der kan acceptere Bukkit plugins, hvis deres jar-filer kopieres til `/plugins` folderen.

# Basale Plugins
Der er en helt masse plugins, der ligesom bare skal være på en minecraft server for at gøre det sjovt.

De følgende plugins er de mest basale i en hver Bukkit server (IMO).

| Plugin | Beskrivelse |
| ------ | ----------- |
| [Essentials](https://hub.spigotmc.org/jenkins/job/Spigot-Essentials/) | Div. `home` kommandoer, `chat`, `spawn`, `kits` mm. |
| [zPermissions](https://hub.spigotmc.org/jenkins/job/zPermissions/) | Et SuperPerms rettigheds plugin, der tillader at man tildeles ranks osv. |
| [PlugMan](http://dev.bukkit.org/bukkit-plugins/plugman/) | Et simpelt men kraftfuldt plugin, der tillader at man loader/unloader plugins imens serveren kører. Dette gør det MEGET lettere at teste sit plugin, da man ikke behøver at genstarte serveren hver gang man har lavet ændringer. |
