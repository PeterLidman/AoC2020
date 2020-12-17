package AoC2020;

public class ITri {	
	private final Integer pf, ps, pt;

	public ITri(Integer first, Integer second, Integer third) {
		this.pf = first;
		this.ps = second;
		this.pt = third;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pf == null) ? 0 : pf.hashCode());
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
		ITri other = (ITri) obj;
		if (pf == null) {
			if (other.pf != null)
				return false;
		} else if (!pf.equals(other.pf))
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
		return "Triple [" + pf + ", " + ps + ", " + pt + "]";
	}
}