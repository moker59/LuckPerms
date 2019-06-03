/*
 * This file is part of luckperms, licensed under the MIT License.
 *
 *  Copyright (c) lucko (Luck) <luck@lucko.me>
 *  Copyright (c) contributors
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package me.lucko.luckperms.api.node;

import me.lucko.luckperms.api.node.types.ChatMetaNode;
import me.lucko.luckperms.api.node.types.DisplayNameNode;
import me.lucko.luckperms.api.node.types.InheritanceNode;
import me.lucko.luckperms.api.node.types.MetaNode;
import me.lucko.luckperms.api.node.types.PermissionNode;
import me.lucko.luckperms.api.node.types.PrefixNode;
import me.lucko.luckperms.api.node.types.RegexPermissionNode;
import me.lucko.luckperms.api.node.types.SuffixNode;
import me.lucko.luckperms.api.node.types.WeightNode;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Represents a type of meta
 */
public interface NodeType<T extends Node> {

    /**
     * 
     */
    NodeType<PermissionNode> PERMISSION = new NodeType<PermissionNode>() {
        @Override
        public boolean matches(Node node) {
            Objects.requireNonNull(node, "node");
            return node instanceof PermissionNode;
        }

        @Override
        public PermissionNode cast(Node node) {
            if (!matches(node)) {
                throw new IllegalArgumentException("Node " + node.getClass() + " does not match " + this);
            }
            return ((PermissionNode) node);
        }
    };

    /**
     * 
     */
    NodeType<RegexPermissionNode> REGEX_PERMISSION = new NodeType<RegexPermissionNode>() {
        @Override
        public boolean matches(Node node) {
            Objects.requireNonNull(node, "node");
            return node instanceof RegexPermissionNode;
        }

        @Override
        public RegexPermissionNode cast(Node node) {
            if (!matches(node)) {
                throw new IllegalArgumentException("Node " + node.getClass() + " does not match " + this);
            }
            return ((RegexPermissionNode) node);
        }
    };

    /**
     * 
     */
    NodeType<InheritanceNode> INHERITANCE = new NodeType<InheritanceNode>() {
        @Override
        public boolean matches(Node node) {
            Objects.requireNonNull(node, "node");
            return node instanceof InheritanceNode;
        }

        @Override
        public InheritanceNode cast(Node node) {
            if (!matches(node)) {
                throw new IllegalArgumentException("Node " + node.getClass() + " does not match " + this);
            }
            return ((InheritanceNode) node);
        }
    };

    /**
     * Represents a prefix
     */
    NodeType<PrefixNode> PREFIX = new NodeType<PrefixNode>() {
        @Override
        public boolean matches(Node node) {
            Objects.requireNonNull(node, "node");
            return node instanceof PrefixNode;
        }

        @Override
        public PrefixNode cast(Node node) {
            if (!matches(node)) {
                throw new IllegalArgumentException("Node " + node.getClass() + " does not match " + this);
            }
            return ((PrefixNode) node);
        }
    };

    /**
     * Represents a suffix
     */
    NodeType<SuffixNode> SUFFIX = new NodeType<SuffixNode>() {
        @Override
        public boolean matches(Node node) {
            Objects.requireNonNull(node, "node");
            return node instanceof SuffixNode;
        }

        @Override
        public SuffixNode cast(Node node) {
            if (!matches(node)) {
                throw new IllegalArgumentException("Node " + node.getClass() + " does not match " + this);
            }
            return ((SuffixNode) node);
        }
    };

    /**
     * Represents a meta key-value pair
     */
    NodeType<MetaNode> META = new NodeType<MetaNode>() {
        @Override
        public boolean matches(Node node) {
            Objects.requireNonNull(node, "node");
            return node instanceof MetaNode;
        }

        @Override
        public MetaNode cast(Node node) {
            if (!matches(node)) {
                throw new IllegalArgumentException("Node " + node.getClass() + " does not match " + this);
            }
            return ((MetaNode) node);
        }
    };

    /**
     * 
     */
    NodeType<WeightNode> WEIGHT = new NodeType<WeightNode>() {
        @Override
        public boolean matches(Node node) {
            Objects.requireNonNull(node, "node");
            return node instanceof WeightNode;
        }

        @Override
        public WeightNode cast(Node node) {
            if (!matches(node)) {
                throw new IllegalArgumentException("Node " + node.getClass() + " does not match " + this);
            }
            return ((WeightNode) node);
        }
    };

    /**
     * 
     */
    NodeType<DisplayNameNode> DISPLAY_NAME = new NodeType<DisplayNameNode>() {
        @Override
        public boolean matches(Node node) {
            Objects.requireNonNull(node, "node");
            return node instanceof DisplayNameNode;
        }

        @Override
        public DisplayNameNode cast(Node node) {
            if (!matches(node)) {
                throw new IllegalArgumentException("Node " + node.getClass() + " does not match " + this);
            }
            return ((DisplayNameNode) node);
        }
    };

    /**
     * Represents any meta type
     */
    NodeType<Node> META_OR_CHAT_META = new NodeType<Node>() {
        @Override
        public boolean matches(Node node) {
            Objects.requireNonNull(node, "node");
            return META.matches(node) || CHAT_META.matches(node);
        }

        @Override
        public Node cast(Node node) {
            if (!matches(node)) {
                throw new IllegalArgumentException("Node " + node.getClass() + " does not match " + this);
            }
            return node;
        }
    };

    /**
     * Represents any chat meta type
     */
    NodeType<ChatMetaNode<?, ?>> CHAT_META = new NodeType<ChatMetaNode<?, ?>>() {
        @Override
        public boolean matches(Node node) {
            Objects.requireNonNull(node, "node");
            return node instanceof ChatMetaNode<?, ?>;
        }

        @Override
        public ChatMetaNode<?, ?> cast(Node node) {
            if (!matches(node)) {
                throw new IllegalArgumentException("Node " + node.getClass() + " does not match " + this);
            }
            return (ChatMetaNode<?, ?>) node;
        }
    };

    /**
     * Returns if the passed node matches the type
     *
     * @param node the node to test
     * @return true if the node has the same type
     */
    boolean matches(Node node);

    /**
     * 
     * @param node
     * @return
     */
    T cast(Node node);

    default Optional<T> tryCast(Node node) {
        Objects.requireNonNull(node, "node");
        if (!matches(node)) {
            return Optional.empty();
        } else {
            return Optional.of(cast(node));
        }
    }

    default Predicate<? super Node> predicate() {
        return this::matches;
    }

    default Predicate<? extends Node> predicate(Predicate<? super T> and) {
        return node -> matches(node) && and.test(cast(node));
    }

}
