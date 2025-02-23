package io.github.gdrfgdrf.cuteverification.web.mediator.utils

object ClassRelationResolver {
    fun findChild(clazz: Class<*>, childName: String): Class<*> {
        val childPackage = clazz.name + "$" + childName
        return Class.forName(childPackage)
    }

    fun isImplement(clazz: Class<*>, target: Class<*>): Boolean {
        if (target.isAssignableFrom(clazz)) {
            return true
        }

        // 检查父类
        var currentClass = clazz.superclass
        while (true) {
            if (currentClass == null || currentClass == Any::class.java || currentClass == Object::class.java) {
                break
            }
            if (isImplement(currentClass, target)) {
                return true
            }
            currentClass = currentClass.superclass
        }

        // 检查父接口
        val checkedInterfaces = mutableSetOf<Class<*>>()
        val checkList = arrayListOf<Class<*>>()
        checkList.addAll(clazz.interfaces)
        while (true) {
            if (checkList.isEmpty()) {
                break
            }
            val currentInterface = checkList.removeAt(0)
            if (checkedInterfaces.contains(currentInterface)) {
                continue
            }
            checkedInterfaces.add(currentInterface)

            if (isImplement(currentInterface, target)) {
                return true
            }
            checkList.addAll(currentInterface.interfaces)
        }

        return false
    }

    fun isInherit(clazz: Class<*>, target: Class<*>): Boolean {
        if (clazz.superclass == java.lang.Enum::class.java ||
            clazz.superclass == Enum::class.java) {
            return target == java.lang.Enum::class.java ||
                    target == Enum::class.java
        }
        if (clazz.superclass == java.lang.Object::class.java ||
            clazz.superclass == Any::class.java) {
            return target == java.lang.Object::class.java ||
                    target == Any::class.java
        }
        if (clazz.superclass == target) {
            return true
        }
        if (clazz.superclass == null) {
            return false
        }
        return isInherit(clazz.superclass, target)
    }
}