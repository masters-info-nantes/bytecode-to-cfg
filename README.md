bytecode-to-cfg [![Build Status](https://travis-ci.org/masterALMA2016/bytecode-to-cfg.svg?branch=master)](https://travis-ci.org/masterALMA2016/bytecode-to-cfg)
===============

Description
-----------
Generation of a Control Flow Graph from a java bytecode using ASM

Requirements
-----------
- [Control flow graph](https://github.com/masterALMA2016/control-flow-graph) installed

Developer
---------
You must be located in project folder (where pom.xml file is) 
to compile with :
```
mvn compile
```

and then run project :
```
mvn exec:java
```

Links
-------------------------------
- [OpCode list and details](http://www.cs.au.dk/~mis/dOvs/jvmspec/ref-Java.html)
- [ASM OpCode list](http://asm.ow2.org/asm33/javadoc/user/constant-values.html)
- [Method visitor](http://asm.ow2.org/asm33/javadoc/user/org/objectweb/asm/MethodVisitor.html)

Usage
------
The method analyzed by the program is in AnalysedClass and its name must be set in visitMethod (ClassAnalyser class)
