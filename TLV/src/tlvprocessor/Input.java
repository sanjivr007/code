package tlvprocessor;

public class Input implements Cloneable {
	String Type;
	int Length;
	String Value;

	public Input() {
	}

	public Input(Input input) {
		this.Type = input.getType();
		this.Length = input.getLength();
		this.Value = input.getValue();

	}

	public Input(String Type, int Length, String Value) {
		this.Type = Type;
		this.Length = Length;
		this.Value = Value;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public int getLength() {
		return Length;
	}

	public void setLength(int length) {
		Length = length;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Length;
		result = prime * result + ((Type == null) ? 0 : Type.hashCode());
		result = prime * result + ((Value == null) ? 0 : Value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Input other = (Input) obj;
		if (Length != other.Length)
			return false;
		if (Type == null) {
			if (other.Type != null)
				return false;
		} else if (!Type.equals(other.Type))
			return false;
		if (Value == null) {
			if (other.Value != null)
				return false;
		} else if (!Value.equals(other.Value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Input [Type=" + Type + ", Length=" + Length + ", Value=" + Value + "]";
	}

}
