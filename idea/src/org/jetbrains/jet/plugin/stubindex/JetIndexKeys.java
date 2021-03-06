/*
 * Copyright 2010-2012 JetBrains s.r.o.
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

package org.jetbrains.jet.plugin.stubindex;

import com.intellij.psi.stubs.StubIndexKey;
import org.jetbrains.jet.lang.psi.JetClassOrObject;
import org.jetbrains.jet.lang.psi.JetNamedFunction;

/**
 * @author Nikolay Krasko
 */
public interface JetIndexKeys {
    StubIndexKey<String, JetClassOrObject> SHORT_NAME_KEY = StubIndexKey.createIndexKey("jet.class.shortName");
    StubIndexKey<String, JetClassOrObject> FQN_KEY = StubIndexKey.createIndexKey("jet.fqn");

    StubIndexKey<String, JetNamedFunction> TOP_LEVEL_FUNCTION_SHORT_NAME_KEY = StubIndexKey.createIndexKey("jet.top.level.function.short.name");
    StubIndexKey<String, JetNamedFunction> TOP_LEVEL_FUNCTION_FQNAME_KEY = StubIndexKey.createIndexKey("jet.top.level.function.fqname");

    StubIndexKey<String, JetNamedFunction> EXTENSION_FUNCTION_SHORT_NAME_KEY = StubIndexKey.createIndexKey("jet.top.level.extension.function.short.name");
    StubIndexKey<String, JetNamedFunction> EXTENSION_FUNCTION_FQNAME_KEY = StubIndexKey.createIndexKey("jet.top.level.extension.function.fqname");
}

