#set($h1 = '#')
#set($h2 = '##')
$h1 ${artifactId}

From [Apache Wicket Quickstart](https://wicket.apache.org/start/quickstart.html)

$h2 Links

***Project***
- [Start Page](http://127.0.0.1:8080/${artifactId}/)

***Wicket***
- [Wicket Homepage](http://wicket.apache.org/)
- [Wicket User Guide (9.x)](https://ci.apache.org/projects/wicket/guide/9.x/single.html)
- [Wicket Wiki](https://cwiki.apache.org/confluence/display/WICKET)
- [Wicket Stuff](https://wicketstuff.org/)
- [Wicket Stuff Wiki](https://github.com/wicketstuff/core/wiki)
- [Introduction on Baeldung](https://www.baeldung.com/intro-to-the-wicket-framework)

***Bootstrap***
- [Bootstrap](https://getbootstrap.com/)
- [Wicket-Bootstrap](https://github.com/l0rdn1kk0n/wicket-bootstrap)
- [Bootswatch (Themes)](https://bootswatch.com/)
- [Boot Snipp](https://bootsnipp.com/)
- [Font Awesome](https://fontawesome.com/)

***HTML***
- [SelfHTML](https://selfhtml.org/) - [Farbpalette](https://wiki.selfhtml.org/wiki/Grafik/Farbpaletten)
- [W3C](https://www.w3.org/) - [HTML 5.3](https://www.w3.org/TR/2018/WD-html53-20181018/)
- [HTML Living Standard (WHATWG)](https://html.spec.whatwg.org/multipage/)
- [Can I use?](https://caniuse.com/)
- [W3 Schools](https://www.w3schools.com/)
- [Mozilla Developers](https://developer.mozilla.org/de/)
- [Dev Docs](https://devdocs.io/)

$h2 VM Options

```
-Dfile.encoding=UTF-8
-Dconsole.encoding=UTF-8
-Xms512m
-Xmx4096m
-Dwicket.configuration=development
```

$h2 Useful Maven Commands

```bash
mvn versions:display-property-updates
```
