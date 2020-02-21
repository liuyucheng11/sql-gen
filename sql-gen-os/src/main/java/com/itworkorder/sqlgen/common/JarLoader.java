package com.itworkorder.sqlgen.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.URLClassLoader;
import java.util.jar.JarEntry;

/**
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-2-20  15:46
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class JarLoader {
    private URLClassLoader classLoader;
    private JarEntry entry;
}
