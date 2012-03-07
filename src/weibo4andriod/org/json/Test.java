// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod.org.json;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Iterator;

// Referenced classes of package weibo4andriod.org.json:
//            XML, JSONObject, JSONML, JSONArray, 
//            JSONStringer, JSONWriter, HTTP, CookieList, 
//            Cookie, JSONTokener, CDL

public class Test
{

    public Test()
    {
    }

    public static void main(String args[])
    {
        _cls1Obj _lcls1obj = new _cls1Obj("A beany object", 42D, true);
        JSONObject jsonobject = XML.toJSONObject("<![CDATA[This is a collection of test patterns and examples for org.json.]]>  Ignore the stuff past the end.  ");
        System.out.println(jsonobject.toString());
        JSONObject jsonobject1 = new JSONObject("{     \"list of lists\" : [         [1, 2, 3],         [4, 5, 6],     ] }");
        System.out.println(jsonobject1.toString(4));
        System.out.println(XML.toString(jsonobject1));
        JSONObject jsonobject2 = XML.toJSONObject("<recipe name=\"bread\" prep_time=\"5 mins\" cook_time=\"3 hours\"> <title>Basic bread</title> <ingredient amount=\"8\" unit=\"dL\">Flour</ingredient> <ingredient amount=\"10\" unit=\"grams\">Yeast</ingredient> <ingredient amount=\"4\" unit=\"dL\" state=\"warm\">Water</ingredient> <ingredient amount=\"1\" unit=\"teaspoon\">Salt</ingredient> <instructions> <step>Mix all ingredients together.</step> <step>Knead thoroughly.</step> <step>Cover with a cloth, and leave for one hour in warm room.</step> <step>Knead again.</step> <step>Place in a bread baking tin.</step> <step>Cover with a cloth, and leave for one hour in warm room.</step> <step>Bake in the oven at 180(degrees)C for 30 minutes.</step> </instructions> </recipe> ");
        System.out.println(jsonobject2.toString(4));
        System.out.println();
        JSONObject jsonobject3 = JSONML.toJSONObject("<recipe name=\"bread\" prep_time=\"5 mins\" cook_time=\"3 hours\"> <title>Basic bread</title> <ingredient amount=\"8\" unit=\"dL\">Flour</ingredient> <ingredient amount=\"10\" unit=\"grams\">Yeast</ingredient> <ingredient amount=\"4\" unit=\"dL\" state=\"warm\">Water</ingredient> <ingredient amount=\"1\" unit=\"teaspoon\">Salt</ingredient> <instructions> <step>Mix all ingredients together.</step> <step>Knead thoroughly.</step> <step>Cover with a cloth, and leave for one hour in warm room.</step> <step>Knead again.</step> <step>Place in a bread baking tin.</step> <step>Cover with a cloth, and leave for one hour in warm room.</step> <step>Bake in the oven at 180(degrees)C for 30 minutes.</step> </instructions> </recipe> ");
        System.out.println(jsonobject3.toString());
        System.out.println(JSONML.toString(jsonobject3));
        System.out.println();
        JSONArray jsonarray = JSONML.toJSONArray("<recipe name=\"bread\" prep_time=\"5 mins\" cook_time=\"3 hours\"> <title>Basic bread</title> <ingredient amount=\"8\" unit=\"dL\">Flour</ingredient> <ingredient amount=\"10\" unit=\"grams\">Yeast</ingredient> <ingredient amount=\"4\" unit=\"dL\" state=\"warm\">Water</ingredient> <ingredient amount=\"1\" unit=\"teaspoon\">Salt</ingredient> <instructions> <step>Mix all ingredients together.</step> <step>Knead thoroughly.</step> <step>Cover with a cloth, and leave for one hour in warm room.</step> <step>Knead again.</step> <step>Place in a bread baking tin.</step> <step>Cover with a cloth, and leave for one hour in warm room.</step> <step>Bake in the oven at 180(degrees)C for 30 minutes.</step> </instructions> </recipe> ");
        System.out.println(jsonarray.toString(4));
        System.out.println(JSONML.toString(jsonarray));
        System.out.println();
        JSONObject jsonobject4 = JSONML.toJSONObject("<div id=\"demo\" class=\"JSONML\"><p>JSONML is a transformation between <b>JSON</b> and <b>XML</b> that preserves ordering of document features.</p><p>JSONML can work with JSON arrays or JSON objects.</p><p>Three<br/>little<br/>words</p></div>");
        System.out.println(jsonobject4.toString(4));
        System.out.println(JSONML.toString(jsonobject4));
        System.out.println();
        JSONArray jsonarray1 = JSONML.toJSONArray("<div id=\"demo\" class=\"JSONML\"><p>JSONML is a transformation between <b>JSON</b> and <b>XML</b> that preserves ordering of document features.</p><p>JSONML can work with JSON arrays or JSON objects.</p><p>Three<br/>little<br/>words</p></div>");
        System.out.println(jsonarray1.toString(4));
        System.out.println(JSONML.toString(jsonarray1));
        System.out.println();
        JSONObject jsonobject5 = XML.toJSONObject("<person created=\"2006-11-11T19:23\" modified=\"2006-12-31T23:59\">\n <firstName>Robert</firstName>\n <lastName>Smith</lastName>\n <address type=\"home\">\n <street>12345 Sixth Ave</street>\n <city>Anytown</city>\n <state>CA</state>\n <postalCode>98765-4321</postalCode>\n </address>\n </person>");
        System.out.println(jsonobject5.toString(4));
        JSONObject jsonobject6 = new JSONObject(_lcls1obj);
        System.out.println(jsonobject6.toString());
        JSONObject jsonobject7 = new JSONObject("{ \"entity\": { \"imageURL\": \"\", \"name\": \"IXXXXXXXXXXXXX\", \"id\": 12336, \"ratingCount\": null, \"averageRating\": null } }");
        System.out.println(jsonobject7.toString(2));
        String s = (new JSONStringer()).object().key("single").value("MARIE HAA'S").key("Johnny").value("MARIE HAA\\'S").key("foo").value("bar").key("baz").array().object().key("quux").value("Thanks, Josh!").endObject().endArray().key("obj keys").value(JSONObject.getNames(_lcls1obj)).endObject().toString();
        System.out.println(s);
        System.out.println((new JSONStringer()).object().key("a").array().array().array().value("b").endArray().endArray().endArray().endObject().toString());
        JSONStringer jsonstringer = new JSONStringer();
        jsonstringer.array();
        jsonstringer.value(1L);
        jsonstringer.array();
        jsonstringer.value(null);
        jsonstringer.array();
        jsonstringer.object();
        jsonstringer.key("empty-array").array().endArray();
        jsonstringer.key("answer").value(42L);
        jsonstringer.key("null").value(null);
        jsonstringer.key("false").value(false);
        jsonstringer.key("true").value(true);
        jsonstringer.key("big").value(1.2345678900000001E+096D);
        jsonstringer.key("small").value(1.2345678899999999E-080D);
        jsonstringer.key("empty-object").object().endObject();
        jsonstringer.key("long");
        jsonstringer.value(0x7fffffffffffffffL);
        jsonstringer.endObject();
        jsonstringer.value("two");
        jsonstringer.endArray();
        jsonstringer.value(true);
        jsonstringer.endArray();
        jsonstringer.value(98.599999999999994D);
        jsonstringer.value(-100D);
        jsonstringer.object();
        jsonstringer.endObject();
        jsonstringer.object();
        jsonstringer.key("one");
        jsonstringer.value(1.0D);
        jsonstringer.endObject();
        jsonstringer.value(_lcls1obj);
        jsonstringer.endArray();
        System.out.println(jsonstringer.toString());
        System.out.println((new JSONArray(jsonstringer.toString())).toString(4));
        int ai[] = new int[3];
        ai[0] = 1;
        ai[1] = 2;
        ai[2] = 3;
        JSONArray jsonarray2 = new JSONArray(ai);
        System.out.println(jsonarray2.toString());
        String args1[] = new String[3];
        args1[0] = "aString";
        args1[1] = "aNumber";
        args1[2] = "aBoolean";
        JSONObject jsonobject8 = new JSONObject(_lcls1obj, args1);
        jsonobject8.put("Testing JSONString interface", _lcls1obj);
        System.out.println(jsonobject8.toString(4));
        JSONObject jsonobject9 = new JSONObject("{slashes: '///', closetag: '</script>', backslash:'\\\\', ei: {quotes: '\"\\''},eo: {a: '\"quoted\"', b:\"don't\"}, quotes: [\"'\", '\"']}");
        System.out.println(jsonobject9.toString(2));
        System.out.println(XML.toString(jsonobject9));
        System.out.println("");
        JSONObject jsonobject10 = new JSONObject("{foo: [true, false,9876543210,    0.0, 1.00000001,  1.000000000001, 1.00000000000000001, .00000000000000001, 2.00, 0.1, 2e100, -32,[],{}, \"string\"],   to   : null, op : 'Good',ten:10} postfix comment");
        jsonobject10.put("String", "98.6");
        jsonobject10.put("JSONObject", new JSONObject());
        jsonobject10.put("JSONArray", new JSONArray());
        jsonobject10.put("int", 57);
        jsonobject10.put("double", 1.2345678901234568E+029D);
        jsonobject10.put("true", true);
        jsonobject10.put("false", false);
        jsonobject10.put("null", JSONObject.NULL);
        jsonobject10.put("bool", "true");
        jsonobject10.put("zero", 0.0D);
        jsonobject10.put("\\u2028", "\u2028");
        jsonobject10.put("\\u2029", "\u2029");
        JSONArray jsonarray3 = jsonobject10.getJSONArray("foo");
        jsonarray3.put(666);
        jsonarray3.put(2001.99D);
        jsonarray3.put("so \"fine\".");
        jsonarray3.put("so <fine>.");
        jsonarray3.put(true);
        jsonarray3.put(false);
        jsonarray3.put(new JSONArray());
        jsonarray3.put(new JSONObject());
        jsonobject10.put("keys", JSONObject.getNames(jsonobject10));
        System.out.println(jsonobject10.toString(4));
        System.out.println(XML.toString(jsonobject10));
        System.out.println((new StringBuilder()).append("String: ").append(jsonobject10.getDouble("String")).toString());
        System.out.println((new StringBuilder()).append("  bool: ").append(jsonobject10.getBoolean("bool")).toString());
        System.out.println((new StringBuilder()).append("    to: ").append(jsonobject10.getString("to")).toString());
        System.out.println((new StringBuilder()).append("  true: ").append(jsonobject10.getString("true")).toString());
        System.out.println((new StringBuilder()).append("   foo: ").append(jsonobject10.getJSONArray("foo")).toString());
        System.out.println((new StringBuilder()).append("    op: ").append(jsonobject10.getString("op")).toString());
        System.out.println((new StringBuilder()).append("   ten: ").append(jsonobject10.getInt("ten")).toString());
        System.out.println((new StringBuilder()).append("  oops: ").append(jsonobject10.optBoolean("oops")).toString());
        JSONObject jsonobject11 = XML.toJSONObject("<xml one = 1 two=' \"2\" '><five></five>First \t&lt;content&gt;<five></five> This is \"content\". <three>  3  </three>JSON does not preserve the sequencing of elements and contents.<three>  III  </three>  <three>  T H R E E</three><four/>Content text is an implied structure in XML. <six content=\"6\"/>JSON does not have implied structure:<seven>7</seven>everything is explicit.<![CDATA[CDATA blocks<are><supported>!]]></xml>");
        System.out.println(jsonobject11.toString(2));
        System.out.println(XML.toString(jsonobject11));
        System.out.println("");
        JSONArray jsonarray4 = JSONML.toJSONArray("<xml one = 1 two=' \"2\" '><five></five>First \t&lt;content&gt;<five></five> This is \"content\". <three>  3  </three>JSON does not preserve the sequencing of elements and contents.<three>  III  </three>  <three>  T H R E E</three><four/>Content text is an implied structure in XML. <six content=\"6\"/>JSON does not have implied structure:<seven>7</seven>everything is explicit.<![CDATA[CDATA blocks<are><supported>!]]></xml>");
        System.out.println(jsonarray4.toString(4));
        System.out.println(JSONML.toString(jsonarray4));
        System.out.println("");
        JSONArray jsonarray5 = JSONML.toJSONArray("<xml do='0'>uno<a re='1' mi='2'>dos<b fa='3'/>tres<c>true</c>quatro</a>cinqo<d>seis<e/></d></xml>");
        System.out.println(jsonarray5.toString(4));
        System.out.println(JSONML.toString(jsonarray5));
        System.out.println("");
        JSONObject jsonobject12 = XML.toJSONObject("<mapping><empty/>   <class name = \"Customer\">      <field name = \"ID\" type = \"string\">         <bind-xml name=\"ID\" node=\"attribute\"/>      </field>      <field name = \"FirstName\" type = \"FirstName\"/>      <field name = \"MI\" type = \"MI\"/>      <field name = \"LastName\" type = \"LastName\"/>   </class>   <class name = \"FirstName\">      <field name = \"text\">         <bind-xml name = \"text\" node = \"text\"/>      </field>   </class>   <class name = \"MI\">      <field name = \"text\">         <bind-xml name = \"text\" node = \"text\"/>      </field>   </class>   <class name = \"LastName\">      <field name = \"text\">         <bind-xml name = \"text\" node = \"text\"/>      </field>   </class></mapping>");
        System.out.println(jsonobject12.toString(2));
        System.out.println(XML.toString(jsonobject12));
        System.out.println("");
        JSONArray jsonarray6 = JSONML.toJSONArray("<mapping><empty/>   <class name = \"Customer\">      <field name = \"ID\" type = \"string\">         <bind-xml name=\"ID\" node=\"attribute\"/>      </field>      <field name = \"FirstName\" type = \"FirstName\"/>      <field name = \"MI\" type = \"MI\"/>      <field name = \"LastName\" type = \"LastName\"/>   </class>   <class name = \"FirstName\">      <field name = \"text\">         <bind-xml name = \"text\" node = \"text\"/>      </field>   </class>   <class name = \"MI\">      <field name = \"text\">         <bind-xml name = \"text\" node = \"text\"/>      </field>   </class>   <class name = \"LastName\">      <field name = \"text\">         <bind-xml name = \"text\" node = \"text\"/>      </field>   </class></mapping>");
        System.out.println(jsonarray6.toString(4));
        System.out.println(JSONML.toString(jsonarray6));
        System.out.println("");
        JSONObject jsonobject13 = XML.toJSONObject("<?xml version=\"1.0\" ?><Book Author=\"Anonymous\"><Title>Sample Book</Title><Chapter id=\"1\">This is chapter 1. It is not very long or interesting.</Chapter><Chapter id=\"2\">This is chapter 2. Although it is longer than chapter 1, it is not any more interesting.</Chapter></Book>");
        System.out.println(jsonobject13.toString(2));
        System.out.println(XML.toString(jsonobject13));
        System.out.println("");
        JSONObject jsonobject14 = XML.toJSONObject("<!DOCTYPE bCard 'http://www.cs.caltech.edu/~adam/schemas/bCard'><bCard><?xml default bCard        firstname = ''        lastname  = '' company   = '' email = '' homepage  = ''?><bCard        firstname = 'Rohit'        lastname  = 'Khare'        company   = 'MCI'        email     = 'khare@mci.net'        homepage  = 'http://pest.w3.org/'/><bCard        firstname = 'Adam'        lastname  = 'Rifkin'        company   = 'Caltech Infospheres Project'        email     = 'adam@cs.caltech.edu'        homepage  = 'http://www.cs.caltech.edu/~adam/'/></bCard>");
        System.out.println(jsonobject14.toString(2));
        System.out.println(XML.toString(jsonobject14));
        System.out.println("");
        JSONObject jsonobject15 = XML.toJSONObject("<?xml version=\"1.0\"?><customer>    <firstName>        <text>Fred</text>    </firstName>    <ID>fbs0001</ID>    <lastName> <text>Scerbo</text>    </lastName>    <MI>        <text>B</text>    </MI></customer>");
        System.out.println(jsonobject15.toString(2));
        System.out.println(XML.toString(jsonobject15));
        System.out.println("");
        JSONObject jsonobject16 = XML.toJSONObject("<!ENTITY tp-address PUBLIC '-//ABC University::Special Collections Library//TEXT (titlepage: name and address)//EN' 'tpspcoll.sgm'><list type='simple'><head>Repository Address </head><item>Special Collections Library</item><item>ABC University</item><item>Main Library, 40 Circle Drive</item><item>Ourtown, Pennsylvania</item><item>17654 USA</item></list>");
        System.out.println(jsonobject16.toString());
        System.out.println(XML.toString(jsonobject16));
        System.out.println("");
        JSONObject jsonobject17 = XML.toJSONObject("<test intertag status=ok><empty/>deluxe<blip sweet=true>&amp;&quot;toot&quot;&toot;&#x41;</blip><x>eks</x><w>bonus</w><w>bonus2</w></test>");
        System.out.println(jsonobject17.toString(2));
        System.out.println(XML.toString(jsonobject17));
        System.out.println("");
        JSONObject jsonobject18 = HTTP.toJSONObject("GET / HTTP/1.0\nAccept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*\nAccept-Language: en-us\nUser-Agent: Mozilla/4.0 (compatible; MSIE 5.5; Windows 98; Win 9x 4.90; T312461; Q312461)\nHost: www.nokko.com\nConnection: keep-alive\nAccept-encoding: gzip, deflate\n");
        System.out.println(jsonobject18.toString(2));
        System.out.println(HTTP.toString(jsonobject18));
        System.out.println("");
        JSONObject jsonobject19 = HTTP.toJSONObject("HTTP/1.1 200 Oki Doki\nDate: Sun, 26 May 2002 17:38:52 GMT\nServer: Apache/1.3.23 (Unix) mod_perl/1.26\nKeep-Alive: timeout=15, max=100\nConnection: Keep-Alive\nTransfer-Encoding: chunked\nContent-Type: text/html\n");
        System.out.println(jsonobject19.toString(2));
        System.out.println(HTTP.toString(jsonobject19));
        System.out.println("");
        JSONObject jsonobject20 = new JSONObject("{nix: null, nux: false, null: 'null', 'Request-URI': '/', Method: 'GET', 'HTTP-Version': 'HTTP/1.0'}");
        System.out.println(jsonobject20.toString(2));
        System.out.println((new StringBuilder()).append("isNull: ").append(jsonobject20.isNull("nix")).toString());
        System.out.println((new StringBuilder()).append("   has: ").append(jsonobject20.has("nix")).toString());
        System.out.println(XML.toString(jsonobject20));
        System.out.println(HTTP.toString(jsonobject20));
        System.out.println("");
        JSONObject jsonobject21 = XML.toJSONObject("<?xml version='1.0' encoding='UTF-8'?>\n\n<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/1999/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/1999/XMLSchema\"><SOAP-ENV:Body><ns1:doGoogleSearch xmlns:ns1=\"urn:GoogleSearch\" SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"><key xsi:type=\"xsd:string\">GOOGLEKEY</key> <q xsi:type=\"xsd:string\">'+search+'</q> <start xsi:type=\"xsd:int\">0</start> <maxResults xsi:type=\"xsd:int\">10</maxResults> <filter xsi:type=\"xsd:boolean\">true</filter> <restrict xsi:type=\"xsd:string\"></restrict> <safeSearch xsi:type=\"xsd:boolean\">false</safeSearch> <lr xsi:type=\"xsd:string\"></lr> <ie xsi:type=\"xsd:string\">latin1</ie> <oe xsi:type=\"xsd:string\">latin1</oe></ns1:doGoogleSearch></SOAP-ENV:Body></SOAP-ENV:Envelope>");
        System.out.println(jsonobject21.toString(2));
        System.out.println(XML.toString(jsonobject21));
        System.out.println("");
        JSONObject jsonobject22 = new JSONObject("{Envelope: {Body: {\"ns1:doGoogleSearch\": {oe: \"latin1\", filter: true, q: \"'+search+'\", key: \"GOOGLEKEY\", maxResults: 10, \"SOAP-ENV:encodingStyle\": \"http://schemas.xmlsoap.org/soap/encoding/\", start: 0, ie: \"latin1\", safeSearch:false, \"xmlns:ns1\": \"urn:GoogleSearch\"}}}}");
        System.out.println(jsonobject22.toString(2));
        System.out.println(XML.toString(jsonobject22));
        System.out.println("");
        JSONObject jsonobject23 = CookieList.toJSONObject("  f%oo = b+l=ah  ; o;n%40e = t.wo ");
        System.out.println(jsonobject23.toString(2));
        System.out.println(CookieList.toString(jsonobject23));
        System.out.println("");
        JSONObject jsonobject24 = Cookie.toJSONObject("f%oo=blah; secure ;expires = April 24, 2002");
        System.out.println(jsonobject24.toString(2));
        System.out.println(Cookie.toString(jsonobject24));
        System.out.println("");
        JSONObject jsonobject25 = new JSONObject("{script: 'It is not allowed in HTML to send a close script tag in a string<script>because it confuses browsers</script>so we insert a backslash before the /'}");
        System.out.println(jsonobject25.toString());
        System.out.println("");
        JSONTokener jsontokener = new JSONTokener("{op:'test', to:'session', pre:1}{op:'test', to:'session', pre:2}");
        JSONObject jsonobject26 = new JSONObject(jsontokener);
        System.out.println(jsonobject26.toString());
        System.out.println((new StringBuilder()).append("pre: ").append(jsonobject26.optInt("pre")).toString());
        char c = jsontokener.skipTo('{');
        System.out.println(c);
        JSONObject jsonobject27 = new JSONObject(jsontokener);
        System.out.println(jsonobject27.toString());
        System.out.println("");
        JSONArray jsonarray7 = CDL.toJSONArray("No quotes, 'Single Quotes', \"Double Quotes\"\n1,'2',\"3\"\n,'It is \"good,\"', \"It works.\"\n\n");
        System.out.println(CDL.toString(jsonarray7));
        System.out.println("");
        System.out.println(jsonarray7.toString(4));
        System.out.println("");
        JSONArray jsonarray8 = new JSONArray(" [\"<escape>\", next is an implied null , , ok,] ");
        System.out.println(jsonarray8.toString());
        System.out.println("");
        System.out.println(XML.toString(jsonarray8));
        System.out.println("");
        JSONObject jsonobject28 = new JSONObject("{ fun => with non-standard forms ; forgiving => This package can be used to parse formats that are similar to but not stricting conforming to JSON; why=To make it easier to migrate existing data to JSON,one = [[1.00]]; uno=[[{1=>1}]];'+':+6e66 ;pluses=+++;empty = '' , 'double':0.666,true: TRUE, false: FALSE, null=NULL;[true] = [[!,@;*]]; string=>  o. k. ; \r oct=0666; hex=0x666; dec=666; o=0999; noh=0x0x}");
        System.out.println(jsonobject28.toString(4));
        System.out.println("");
        if(jsonobject28.getBoolean("true") && !jsonobject28.getBoolean("false"))
            System.out.println("It's all good");
        System.out.println("");
        String args2[] = new String[4];
        args2[0] = "dec";
        args2[1] = "oct";
        args2[2] = "hex";
        args2[3] = "missing";
        JSONObject jsonobject29 = new JSONObject(jsonobject28, args2);
        System.out.println(jsonobject29.toString(4));
        System.out.println("");
        System.out.println((new JSONStringer()).array().value(jsonarray8).value(jsonobject29).endArray());
        JSONObject jsonobject30 = new JSONObject("{string: \"98.6\", long: 2147483648, int: 2147483647, longer: 9223372036854775807, double: 9223372036854775808}");
        System.out.println(jsonobject30.toString(4));
        System.out.println("\ngetInt");
        System.out.println((new StringBuilder()).append("int    ").append(jsonobject30.getInt("int")).toString());
        System.out.println((new StringBuilder()).append("long   ").append(jsonobject30.getInt("long")).toString());
        System.out.println((new StringBuilder()).append("longer ").append(jsonobject30.getInt("longer")).toString());
        System.out.println((new StringBuilder()).append("double ").append(jsonobject30.getInt("double")).toString());
        System.out.println((new StringBuilder()).append("string ").append(jsonobject30.getInt("string")).toString());
        System.out.println("\ngetLong");
        System.out.println((new StringBuilder()).append("int    ").append(jsonobject30.getLong("int")).toString());
        System.out.println((new StringBuilder()).append("long   ").append(jsonobject30.getLong("long")).toString());
        System.out.println((new StringBuilder()).append("longer ").append(jsonobject30.getLong("longer")).toString());
        System.out.println((new StringBuilder()).append("double ").append(jsonobject30.getLong("double")).toString());
        System.out.println((new StringBuilder()).append("string ").append(jsonobject30.getLong("string")).toString());
        System.out.println("\ngetDouble");
        System.out.println((new StringBuilder()).append("int    ").append(jsonobject30.getDouble("int")).toString());
        System.out.println((new StringBuilder()).append("long   ").append(jsonobject30.getDouble("long")).toString());
        System.out.println((new StringBuilder()).append("longer ").append(jsonobject30.getDouble("longer")).toString());
        System.out.println((new StringBuilder()).append("double ").append(jsonobject30.getDouble("double")).toString());
        System.out.println((new StringBuilder()).append("string ").append(jsonobject30.getDouble("string")).toString());
        jsonobject30.put("good sized", 0x7fffffffffffffffL);
        System.out.println(jsonobject30.toString(4));
        JSONArray jsonarray9 = new JSONArray("[2147483647, 2147483648, 9223372036854775807, 9223372036854775808]");
        System.out.println(jsonarray9.toString(4));
        System.out.println("\nKeys: ");
        String s1;
        for(Iterator iterator = jsonobject30.keys(); iterator.hasNext(); System.out.println((new StringBuilder()).append(s1).append(": ").append(jsonobject30.getString(s1)).toString()))
            s1 = (String)iterator.next();

          goto _L1
        Exception exception;
        exception;
        System.out.println(exception.toString());
_L2:
        return;
_L1:
        JSONArray jsonarray12;
        JSONObject jsonobject36;
        System.out.println("\naccumulate: ");
        JSONObject jsonobject31 = new JSONObject();
        jsonobject31.accumulate("stooge", "Curly");
        jsonobject31.accumulate("stooge", "Larry");
        jsonobject31.accumulate("stooge", "Moe");
        jsonobject31.getJSONArray("stooge").put(5, "Shemp");
        System.out.println(jsonobject31.toString(4));
        System.out.println("\nwrite:");
        System.out.println(jsonobject31.write(new StringWriter()));
        JSONObject jsonobject32 = XML.toJSONObject("<xml empty><a></a><a>1</a><a>22</a><a>333</a></xml>");
        System.out.println(jsonobject32.toString(4));
        System.out.println(XML.toString(jsonobject32));
        JSONObject jsonobject33 = XML.toJSONObject("<book><chapter>Content of the first chapter</chapter><chapter>Content of the second chapter      <chapter>Content of the first subchapter</chapter>      <chapter>Content of the second subchapter</chapter></chapter><chapter>Third Chapter</chapter></book>");
        System.out.println(jsonobject33.toString(4));
        System.out.println(XML.toString(jsonobject33));
        JSONArray jsonarray10 = JSONML.toJSONArray("<book><chapter>Content of the first chapter</chapter><chapter>Content of the second chapter      <chapter>Content of the first subchapter</chapter>      <chapter>Content of the second subchapter</chapter></chapter><chapter>Third Chapter</chapter></book>");
        System.out.println(jsonarray10.toString(4));
        System.out.println(JSONML.toString(jsonarray10));
        JSONObject jsonobject34 = new JSONObject(null);
        JSONArray jsonarray11 = new JSONArray(null);
        jsonobject34.append("stooge", "Joe DeRita");
        jsonobject34.append("stooge", "Shemp");
        jsonobject34.accumulate("stooges", "Curly");
        jsonobject34.accumulate("stooges", "Larry");
        jsonobject34.accumulate("stooges", "Moe");
        jsonobject34.accumulate("stoogearray", jsonobject34.get("stooges"));
        jsonobject34.put("map", null);
        jsonobject34.put("collection", null);
        jsonobject34.put("array", jsonarray11);
        jsonarray11.put(null);
        jsonarray11.put(null);
        System.out.println(jsonobject34.toString(4));
        JSONObject jsonobject35 = new JSONObject("{plist=Apple; AnimalSmells = { pig = piggish; lamb = lambish; worm = wormy; }; AnimalSounds = { pig = oink; lamb = baa; worm = baa;  Lisa = \"Why is the worm talking like a lamb?\" } ; AnimalColors = { pig = pink; lamb = black; worm = pink; } } ");
        System.out.println(jsonobject35.toString(4));
        jsonarray12 = new JSONArray(" (\"San Francisco\", \"New York\", \"Seoul\", \"London\", \"Seattle\", \"Shanghai\")");
        System.out.println(jsonarray12.toString());
        jsonobject36 = XML.toJSONObject("<a ichi='1' ni='2'><b>The content of b</b> and <c san='3'>The content of c</c><d>do</d><e></e><d>re</d><f/><d>mi</d></a>");
        System.out.println(jsonobject36.toString(2));
        System.out.println(XML.toString(jsonobject36));
        System.out.println("");
        JSONArray jsonarray13 = JSONML.toJSONArray("<a ichi='1' ni='2'><b>The content of b</b> and <c san='3'>The content of c</c><d>do</d><e></e><d>re</d><f/><d>mi</d></a>");
        System.out.println(jsonarray13.toString(4));
        System.out.println(JSONML.toString(jsonarray13));
        System.out.println("");
        System.out.println("\nTesting Exceptions: ");
        System.out.print("Exception: ");
        JSONArray jsonarray14 = new JSONArray();
        jsonarray14.put((-1.0D / 0.0D));
        jsonarray14.put((0.0D / 0.0D));
        System.out.println(jsonarray14.toString());
_L3:
        System.out.print("Exception: ");
        Exception exception2;
        JSONObject jsonobject37;
        JSONObject jsonobject38;
        JSONObject jsonobject40;
        Exception exception17;
        JSONObject jsonobject41;
        JSONObject jsonobject42;
        JSONObject jsonobject43;
        try
        {
            System.out.println(jsonobject36.getDouble("stooge"));
        }
        catch(Exception exception3)
        {
            System.out.println(exception3);
        }
        System.out.print("Exception: ");
        try
        {
            System.out.println(jsonobject36.getDouble("howard"));
        }
        catch(Exception exception4)
        {
            System.out.println(exception4);
        }
        System.out.print("Exception: ");
        try
        {
            System.out.println(jsonobject36.put(null, "howard"));
        }
        catch(Exception exception5)
        {
            System.out.println(exception5);
        }
        System.out.print("Exception: ");
        try
        {
            System.out.println(jsonarray14.getDouble(0));
        }
        catch(Exception exception6)
        {
            System.out.println(exception6);
        }
        System.out.print("Exception: ");
        try
        {
            System.out.println(jsonarray14.get(-1));
        }
        catch(Exception exception7)
        {
            System.out.println(exception7);
        }
        System.out.print("Exception: ");
        try
        {
            System.out.println(jsonarray14.put((0.0D / 0.0D)));
        }
        catch(Exception exception8)
        {
            System.out.println(exception8);
        }
        System.out.print("Exception: ");
        jsonobject43 = XML.toJSONObject("<a><b>    ");
        jsonobject37 = jsonobject43;
_L4:
        System.out.print("Exception: ");
        jsonobject42 = XML.toJSONObject("<a></b>    ");
        jsonobject37 = jsonobject42;
_L5:
        System.out.print("Exception: ");
        jsonobject41 = XML.toJSONObject("<a></a    ");
        jsonobject37 = jsonobject41;
_L6:
        System.out.print("Exception: ");
        Exception exception9;
        Exception exception10;
        Exception exception11;
        try
        {
            JSONArray jsonarray15 = new JSONArray(new Object());
            System.out.println(jsonarray15.toString());
        }
        catch(Exception exception12)
        {
            System.out.println(exception12);
        }
        System.out.print("Exception: ");
        try
        {
            JSONArray jsonarray16 = new JSONArray("[)");
            System.out.println(jsonarray16.toString());
        }
        catch(Exception exception13)
        {
            System.out.println(exception13);
        }
        System.out.print("Exception: ");
        try
        {
            JSONArray jsonarray18 = JSONML.toJSONArray("<xml");
            System.out.println(jsonarray18.toString(4));
        }
        catch(Exception exception14)
        {
            System.out.println(exception14);
        }
        System.out.print("Exception: ");
        try
        {
            JSONArray jsonarray17 = JSONML.toJSONArray("<right></wrong>");
            System.out.println(jsonarray17.toString(4));
        }
        catch(Exception exception15)
        {
            System.out.println(exception15);
        }
        System.out.print("Exception: ");
        jsonobject38 = new JSONObject("{\"koda\": true, \"koda\": true}");
        System.out.println(jsonobject38.toString(4));
        jsonobject40 = jsonobject38;
_L7:
        System.out.print("Exception: ");
        (new JSONStringer()).object().key("bosanda").value("MARIE HAA'S").key("bosanda").value("MARIE HAA\\'S").endObject().toString();
        System.out.println(jsonobject40.toString(4));
          goto _L2
        exception17;
        System.out.println(exception17);
          goto _L2
_L9:
        System.out.println(exception2);
        jsonarray14 = jsonarray12;
          goto _L3
        exception9;
        System.out.println(exception9);
        jsonobject37 = jsonobject36;
          goto _L4
        exception10;
        System.out.println(exception10);
          goto _L5
        exception11;
        System.out.println(exception11);
          goto _L6
_L8:
        Exception exception16;
        System.out.println(exception16);
        JSONObject jsonobject39;
        jsonobject40 = jsonobject39;
          goto _L7
        exception16;
        jsonobject39 = jsonobject38;
          goto _L8
        Exception exception1;
        exception1;
        jsonarray12 = jsonarray14;
        exception2 = exception1;
          goto _L9
        exception2;
          goto _L9
        Exception exception18;
        exception18;
        jsonobject39 = jsonobject37;
        exception16 = exception18;
          goto _L8
    }

    private class _cls1Obj
        implements JSONString
    {

        public String getBENT()
        {
            return "All uppercase key";
        }

        public double getNumber()
        {
            return aNumber;
        }

        public String getString()
        {
            return aString;
        }

        public String getX()
        {
            return "x";
        }

        public boolean isBoolean()
        {
            return aBoolean;
        }

        public String toJSONString()
        {
            return (new StringBuilder()).append("{").append(JSONObject.quote(aString)).append(":").append(JSONObject.doubleToString(aNumber)).append("}").toString();
        }

        public String toString()
        {
            return (new StringBuilder()).append(getString()).append(" ").append(getNumber()).append(" ").append(isBoolean()).append(".").append(getBENT()).append(" ").append(getX()).toString();
        }

        public boolean aBoolean;
        public double aNumber;
        public String aString;

        public _cls1Obj(String s, double d, boolean flag)
        {
            aString = s;
            aNumber = d;
            aBoolean = flag;
        }
    }

}
