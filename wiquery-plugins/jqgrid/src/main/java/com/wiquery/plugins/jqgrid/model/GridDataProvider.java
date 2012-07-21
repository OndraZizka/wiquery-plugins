package com.wiquery.plugins.jqgrid.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.util.SingleSortState;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import com.wiquery.plugins.jqgrid.model.SortInfo;
import com.wiquery.plugins.jqgrid.model.SortOrder;
import com.wiquery.plugins.jqgrid.util.QueryUtils;

public class GridDataProvider<T extends Serializable>
    implements ISortableDataProvider<T> 
{
    private static final long serialVersionUID = 1L;

    private SingleSortState state = new SingleSortState();;
    
    final IModel<? extends List<T>> delegate;
    public GridDataProvider( IModel<? extends List<T>> aDelegate )
    {
        delegate = aDelegate;
    }

    public Iterator<? extends T> iterator( int first, int count )
    {
        final List<T> list = delegate.getObject();

        if( this.state == null || this.state.getSort() == null )
        {
            int toIndex = first + count;
            if (toIndex > list.size())
            {
                toIndex = list.size();
            }
            return list.subList(first, toIndex).listIterator();
        }
        else
        {
            final SortParam sort = this.state.getSort();
            final SortInfo sortInfo = new SortInfo( sort.getProperty(), sort.isAscending()? SortOrder.asc: SortOrder.desc );
            if(sortInfo != null)
                QueryUtils.sortList( list, sortInfo );
            int toIndex = first + count;
            if (toIndex > list.size())
            {
                    toIndex = list.size();
            }
            return list.subList(first, toIndex).listIterator();
        }
    }

    public ISortState getSortState()
    {
        return state;
    }

    public void setSortState( ISortState state )
    {
        this.state = (SingleSortState)state;
    }

    public IModel<T> model( T object )
    {
        return new Model<T>( object );
    }

    public int size()
    {
        final List<T> list = delegate.getObject();
        return list.size();
    }

    public void detach()
    {
        delegate.detach();
    }
}
