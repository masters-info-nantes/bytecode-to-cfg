OpCode used in MethodVisitor class
===============

Links
-------------------------------
- [OpCode list and details](http://www.cs.au.dk/~mis/dOvs/jvmspec/ref-Java.html)
- [ASM OpCode list](http://asm.ow2.org/asm33/javadoc/user/constant-values.html)
- [Method visitor](http://asm.ow2.org/asm33/javadoc/user/org/objectweb/asm/MethodVisitor.html)

visitJumpInsn(opCode, label)
-------------------------------
```
IFEQ (153)
IFNE (154)
IFLT (155)
IFGE (156)
IFGT (157)
IFLE (158)
if(x != y) - IF_ICMPEQ(159) Label(else)
if(x == y) - IF_ICMPNE(160) Label(else) 
if(x >= y) - IF_ICMPLT(161) Label(else)
if(x < y)  - IF_ICMPGE(162) Label(else)
if(x <= y) - IF_ICMPGT(163) Label(else)
if(x > y)  - IF_ICMPLE(164) Label(else)
if(x != y) - IF_ACMPEQ(165) Label(else) - x,y reference object
if(x == y) - IF_ACMPNE(166) Label(else) - x,y reference object
if(){}     - GOTO(167)      Label(after conditions)
JSR (168)
if(x != null) - IFNULL(198)    Label(else) - x,y reference object
if(x == null) - IFNONNULL(199) Label(else) - x,y reference object
```

Templates
------------
visitLabel is a important method which allows you to follow program flow
- Declare and set values with primitive types
```
int sum = 0

visitLabel: L1225701038 (set position on instruction)
visitLineNumber: 9 # L1225701038 (position is on line 9)
visitInsn: 3 (code I_CONST0, use zero constant value)
visitVarInsn: 54 # 2 (code CALOAD, use variable number 2)
visitLabel: L1694203642 (set position on next instruction)
```
```
int sum = 76

Just change line "visitVarInsn: 54 # 2" with "visitIntInsn: 16 # 76" (code BIPUSH, use constant value set to 76)
```

- Conditions
```
if(factor > sum){
	sum = 4;
}

visitLineNumber: 11 # L994935521 (set position on if)
visitVarInsn: 21 # 1 (use factor variable)
visitVarInsn: 21 # 2 (use sum variable)
visitJumpInsn: 164 # L1573625905 (execute if instruction and give next instruction if false)
visitLabel: L1669472530 (next instruction if true)

visitLineNumber: 12 # L1669472530 (process in if block)
visitInsn: 6
visitVarInsn: 54 # 2
visitLabel: L1573625905

visitLineNumber: L1573625905 (after if block)
```
```
if(condition) 
	doSomething()
else 
	doAnotherThing()

visitLineNumber: 11 # L994935521 (if)
visitJumpInsn: 164 # L1573625905
visitLabel: L1669472530

visitLineNumber: 12 # L1669472530 (in if block)
visitLabel: L1950963655

visitLineNumber: 13 # L1950963655 (end if)
visitJumpInsn: 167 # L2065827189
visitLabel: L1573625905

visitLineNumber: 15 # L1573625905 (in else block)
visitLabel: L2065827189

visitLineNumber: 18 # L2065827189 (after else block)
```
```
if(condition) 
	doSomething()
else if(condition)
	doAnotherThing()
else
	doStuff()

visitLineNumber: 11 # L994935521 (if)
visitJumpInsn: 164 # L1573625905
visitLabel: L1669472530

visitLineNumber: 12 # L1669472530 (in if)
visitLabel: L1950963655

visitLineNumber: 13 # L1950963655 (end if)
visitJumpInsn: 167 # L2065827189
visitLabel: L1573625905

visitLineNumber: 14 # L1573625905 (else if)
visitJumpInsn: 162 # L1989444474
visitLabel: L272890728

visitLineNumber: 15 # L272890728 (in else if)
visitLabel: L1596879151

visitLineNumber: 16 # L1596879151 (end else if)
visitJumpInsn: 167 # L2065827189
visitLabel: L1989444474

visitLineNumber: 18 # L1989444474 (in else)
visitLabel: L2065827189

visitLineNumber: 21 # L2065827189 (after else)
```