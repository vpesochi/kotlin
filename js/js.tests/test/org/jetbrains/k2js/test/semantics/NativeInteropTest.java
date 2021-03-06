/*
 * Copyright 2000-2012 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.k2js.test.semantics;

import com.google.common.collect.Lists;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.k2js.test.SingleFileTranslationTest;
import org.jetbrains.k2js.test.utils.JsTestUtils;

import java.util.List;

/**
 * @author Pavel Talanov
 */
public final class NativeInteropTest extends SingleFileTranslationTest {

    @NotNull
    private static final String NATIVE = "native/";

    public NativeInteropTest() {
        super("native/");
    }

    @Override
    protected List<String> additionalJSFiles() {
        List<String> result = Lists.newArrayList(super.additionalJSFiles());
        result.addAll(JsTestUtils.getAllFilesInDir(pathToTestFiles() + NATIVE));
        return result;
    }

    public void testSimple() throws Exception {
        checkFooBoxIsTrue("simple.kt");
    }

    public void testClass() throws Exception {
        checkFooBoxIsTrue("class.kt");
    }

    public void testVararg() throws Exception {
        checkFooBoxIsTrue("vararg.kt");
    }

    public void testUndefined() throws Exception {
        checkFooBoxIsTrue("undefined.kt");
    }
}
