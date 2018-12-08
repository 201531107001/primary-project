Null的处理->Optional
ifPresent:opt.ifPresent(labmda)如果对象不为空则执行传入的labmda
orElse:Optional.ofNullable(user).orElse(user2);
orElseGet:Optional.ofNullable(user).orElseGet( () -> user2);
orElseThrow:空抛出异常