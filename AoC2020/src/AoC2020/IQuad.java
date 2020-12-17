package AoC2020;

public class IQuad {
	private final Integer pf, ps, pt, pfo;

	public IQuad(Integer first, Integer second, Integer third, Integer fourth) {
		this.pf = first;
		this.ps = second;
		this.pt = third;
		this.pfo = fourth;
	}

	public Integer getFirst() {
		return pf;
	}

	public Integer getSecond() {
		return ps;
	}

	public Integer getThird() {
		return pt;
	}

	public Integer getFourth() {
		return pfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pf == null) ? 0 : pf.hashCode());
		result = prime * result + ((pfo == null) ? 0 : pfo.hashCode());
		result = prime * result + ((ps == null) ? 0 : ps.hashCode());
		result = prime * result + ((pt == null) ? 0 : pt.hashCode());
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
		IQuad other = (IQuad) obj;
		if (pf == null) {
			if (other.pf != null)
				return false;
		} else if (!pf.equals(other.pf))
			return false;
		if (pfo == null) {
			if (other.pfo != null)
				return false;
		} else if (!pfo.equals(other.pfo))
			return false;
		if (ps == null) {
			if (other.ps != null)
				return false;
		} else if (!ps.equals(other.ps))
			return false;
		if (pt == null) {
			if (other.pt != null)
				return false;
		} else if (!pt.equals(other.pt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Quad [" + pf + ", " + ps + ", " + pt + ", " + pfo + "]";
	}
}