package com.taotao.cloud.goods.application.flow.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table<T extends Info> implements Info {
    public Table(String type) {
        this.type = type;
    }

    private String type;
    private Map<String, T> table = new HashMap<>();

    public T add(String key, T item) {
        this.table.putIfAbsent(key, item);
        return this.table.get(key);
    }

    public T get(String key) {
        return this.table.get(key);
    }

	@Override
	public String info() {
		if (null == table || table.isEmpty()) {
			return "empty";
		}
		StringBuilder sb = new StringBuilder();
		table.forEach((k, v) -> {
			if (null != v) {
				sb.append(k).append(":").append(v.info());
			}
		});
		return sb.toString();
	}

	public List<String> tables(String linkChat) {
		List<String> result = new ArrayList<>();
		this.table.forEach((k, v) -> {
			if (v instanceof Table) {
				Table<?> subTable = (Table<?>) v;
				List<String> subTableS = subTable.tables(linkChat);
				for (String subTableName : subTableS) {
					result.add(k + linkChat + subTableName);
				}
			} else {
				result.add(k);
			}
		});
		return result;
	}
}
